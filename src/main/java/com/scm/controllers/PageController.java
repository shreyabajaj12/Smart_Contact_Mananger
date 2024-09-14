package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page");
        model.addAttribute("message", "scm");
        model.addAttribute("title", "Shrey");
        model.addAttribute("leetcode", "https://leetcode.com/u/shreyabajaj589/");
        return "home";
    }
    @RequestMapping("/about")
    public String about(Model model){
        return "about";
    }
    @RequestMapping("/service")
    public String services(Model model){
        return "services";
    }
    @RequestMapping("/contact")
    public String contact(Model model){
        return "contact";
    }
    @RequestMapping("/login")
    public String login(Model model){
        return "contact";
    }
    @RequestMapping("/register")
    public String register(Model model){
        return "register";
    }

}
