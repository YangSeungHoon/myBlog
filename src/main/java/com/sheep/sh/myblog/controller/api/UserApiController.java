package com.sheep.sh.myblog.controller.api;

import com.sheep.sh.myblog.dto.ResponseDto;
import com.sheep.sh.myblog.model.RoleType;
import com.sheep.sh.myblog.model.User;
import com.sheep.sh.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    //private final HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        user.setRole(RoleType.USER);
        userService.userJoin(user);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user){
//        User principal =  userService.login(user);
//
//        if(principal != null){
//            session.setAttribute("principal",principal);
//        }
//        return new ResponseDto<>(HttpStatus.OK.value(), 1);
//    }
}
