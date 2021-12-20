package com.sheep.sh.myblog.config.auth;

import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 해주니까
    // username이 DB에 있는지만 확인해주면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username));
        return new PrincipalDetail(principal); // 이 때, 시큐리티의 세션에 user정보가 저장이 된다.
    }
}
