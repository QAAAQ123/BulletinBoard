package com.example.bulletin_board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontEndController {

        @GetMapping
        public String forwardToMainPage(){
            return "forward:/main.html";
        }
}
