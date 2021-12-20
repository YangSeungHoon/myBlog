package com.sheep.sh.myblog.controller;

import com.sheep.sh.myblog.config.auth.PrincipalDetail;
import com.sheep.sh.myblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;


    @GetMapping({"","/"})
    public String index(Model model) {
        model.addAttribute("boards",boardService.getBoardList());
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
