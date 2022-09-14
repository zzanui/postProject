package com.example.firstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class firstController {

    @GetMapping("/hi")
    public String con(Model model){
        model.addAttribute("username","한글");

        return "greetings";
    }


    @GetMapping("/bye")
    public String bye(Model model){
        model.addAttribute("username","잘 가");

        return "greetings";
    }

}
