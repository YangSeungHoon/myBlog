# Spring Security
<hr><br>

## 인증(Authenticaion)과 인가(Authorization)
<br>

**인증**
> 해당 사용자가 본인이 맞는지를 확인하는 절차<br>
> => 로그인 하면서 "지금 로그인 하는 사람이 나 맞아."
> 라고 인증 하는 것.

**인가**
> 인증된 사용자가 요청한 자원에 접근 가능한지를 결정하는 절차<br>
=> 로그인 한 후에, 내가 쓴 게시물을 확인하거나 개인정보를 수정하고 싶을 때, "내가 내 게시물에 접근
> 할거야 허용해줘." 하는 것.
<br><br>

## Architecture Components

* **SecurityContextHolder** - 스프링 시큐리티에서 인증한 대상에 대한 상세 정보를 저장하는 공간.
* **SecurityContext** - *SecurityContextHolder*로 접근할 수 있으며, 현재 인증한 사용자의 *Authentication*을 가지고 있다.
* **Authentication** - 사용자가 제공한 인증용 *credential* 이나 *SecurityContext*에 있는 현재 사용자의 *credential*을 제공하며,
*AuthenticationManager*의 입력으로 사용한다.
  - principal : 사용자를 식별한다. 사용자 이름/비밀번호로 인증할 땐 보통 UserDetails 인스턴스이다.
  - credentials : 주로 비밀번호인데 대부분 유출되지 않도록 사용자를 인증한 다음 비워준다.
  - authorities : 사용자에게 부여한 권한은 GrantedAuthroity로 추상화한다.
* **AuthenticationManager** - 스프링 시큐리티의 필터가 인증을 어떻게 수행할지를 정의하는 API.
* **ProviderManager** - *AuthenticationManager*의 구현체이다.
* **AuthenticationProvider** - *ProviderManager*가 특정 인증 유형을 수행할 때 사용한다.
* **UserDetails** - 인증에 성공하여 생성된 *UserDetails* 객체는 *UsernamePasswordAuthenticationToken*을 생성하기 위해 사용된다.
*UserDetails* 인터페이스의 경우에는 implements 하여 사용하면 된다.
* **UserDetailsService** - *UserDetails* 객체를 반환하는 *loadUserByUsername*라는 메서드만 가지고 있다.
해당 메서드에서는 DB를 연결하고 객체를 가져오는 로직을 구현한다.
* **GrantedAuthority** - 현재 사용자(Principal)가 가지고 있는 권한들이다.
형식은 *ROLE_ADMIN* 과 같이 앞에 ROLE_을 붙여서 사용한다.
<br><br>

## 프로젝트에서의 인증절차와 과정 및 코드.

<img height="450" src="https://user-images.githubusercontent.com/44757063/146915160-24dd651b-166e-4a1a-9b91-7a5f18d78aa9.PNG" width="600"/></img>


### 1. (loginForm.jsp)로그인 페이지에서 아이디, 비밀번호 입력 후 login 버튼을 누르면 Http Request요청이 간다.

`
    <form action="/auth/loginProc" method="post"> "/auth/loginProc"로 post 요청이 간다.
`
<br>
<span style="color:#FBD786">* 위 요청 경로는 따로 Controller에서 받지 않고, WebSecurityConfigurerAdapter를 extends 하고있는 SpringSecurity
클래스에서 설정해주게 된다.</span>

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
http
.csrf().disable() //csrf 토큰 비활성화 (테스트시 걸어두는게 좋다.)
.authorizeRequests()//리퀘스트가 들어오면
.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //해당 경로로 들어오는 요청들은
.permitAll() //모두 허용한다.(누구나 들어올 수 있다.)
.anyRequest() //나머지 다른 요청들은
.authenticated() // 인증이 필요하다.
.and()
.formLogin()
.loginPage("/auth/loginForm")
.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인 해준다.
.defaultSuccessUrl("/");
}
```

<br><br>
### 2. UsernamePasswordAuthenticationFilter가 중간에 가로채서 UsernamePasswordAuthenticationToken을 생성한다.
<span style="color:#2ecc71">* HttpServletRequest에 있는 username과 password로 토큰을 생성한다.( 이 토큰으로 해당 요청을 처리할 수 있는 Provider를 찾는다.)</span>
<br><br>

### 3. 만든 토큰을 AuthenticationManager에게 주고, Provider가 선택된다..

<span style="color:#FBD786">* AuthenticationManager는 인터페이스이고, 구현체는 'ProviderManager'이다. 더하여 이 녀석이 AuthenticationProvider List를 가지고 있어서
직접적인 일은 Authentication Provider가 수행한다.</span><br><br>
<img height="400" src="https://user-images.githubusercontent.com/44757063/146941355-6252d095-96b0-4b28-8a50-bcb721dd1ec2.PNG" width="600"/></img>
<br><br><br>

### 4. UserDetailsService의 loadUserByUsernmae메서드 실행.
<br>
<span style="color:#3498db">- 해당 메서드에서는 Repository에서 user가 있는지 검증하고 가져온다.</span><br>
<span style="color:#3498db">- return 값으로 찾은 User객체가 아닌, UserDetails를 구현하고 있는 PrincipalDetail이 되어야 한다. </span><br>
<span style="color:#3498db">- password의 비교는 securityConfig에 있는 cofigure메서드에서 bcryptEncoder를 이용하여 암호화 하고, DB와 비교한다.</span><br>
<span style="color:#3498db">- return 하면서 시큐리티의 세션에 user의 정보가 저장된다.</span><br>
<br>

```java
    public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username));
        return new PrincipalDetail(principal); // 이 때, 시큐리티의 세션에 user정보가 저장이 된다.
    }
}
```

<br>
<span style="color:#3498db">SecurityBuilder는 AuthenticationManager를 만드는 데 사용된다. 메모리 인증, LDAP 인증,
JDBC 기반 인증, UserDetailsService 및 AuthenticationProvider 를 추가할 수 있다.</span><br>

```java
 @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }
```
<br>

## 이런 방법으로 사용.
<br>
<img height="200" src="https://user-images.githubusercontent.com/44757063/146915281-c66a0f26-4b24-4291-b8a1-58f11be3d851.PNG" width="300"/></img>

### 유저 정보 수정 후, 세션 업데이트
<br>

```java
@PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.updateUser(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
```

<br>
<span style="color:#a29bfe">- 받아온 user를 업데이트 해주고, autenticationManager를 호출하여 인증을 진행한다.</span><br>
<span style="color:#a29bfe">- 앞선 과정에서와 같이 token을 만들어주는데 변경된 username 과 password를 보내준다.</span><br>
<span style="color:#a29bfe">- SecurityContextHolder 안에 있는 Context를 꺼내다가 거기에 새로운 authentication객체를 셋팅한다.</span><br>
<br>

### @AuthenticaionPrincipal 어노테이션으로 로그인된 유저 가져오기.
<br>

```java
@GetMapping("/")
   public String home(@AuthenticationPrincipal PrincipalDetail principal){

       principal.getUsername(); 
       return "index";
   }
```
