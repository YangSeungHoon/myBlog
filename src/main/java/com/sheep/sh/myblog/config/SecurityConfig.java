package com.sheep.sh.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //시큐리티 필터 등록.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//리퀘스트가 들어오면
                .antMatchers("/auth/**") //해당 경로로 들어오는 요청들은
                .permitAll() //모두 허용한다.(누구나 들어올 수 있다.)
                .anyRequest() //나머지 다른 요청들은
                .authenticated() // 인증이 필요하다.
                .and()
                .formLogin()
                .loginPage("/auth/loginForm");
    }
}
