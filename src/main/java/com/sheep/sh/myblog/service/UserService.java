package com.sheep.sh.myblog.service;

import com.sheep.sh.myblog.model.RoleType;
import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void userJoin(User user) {

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


    @Transactional
    public void updateUser(User user) {
        User persistance = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
    }
}