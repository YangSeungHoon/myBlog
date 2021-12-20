package com.sheep.sh.myblog.service;

import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void userJoin(User user) {
        userRepository.save(user);
    }


}
