package com.sheep.sh.myblog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index() {

        return "index";
    }
}
