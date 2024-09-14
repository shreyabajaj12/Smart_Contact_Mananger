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
    @RequestMapping("/services")
    public String services(Model model){
        return "services";
    }
}
