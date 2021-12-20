package com.sheep.sh.myblog.controller;

import com.sheep.sh.myblog.config.auth.PrincipalDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {

    //Security에서 login한 유저 세션 찾기.
    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) {

        return "index";
    }
}
