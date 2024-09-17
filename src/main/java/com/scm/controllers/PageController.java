package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
        UserForm userForm = new UserForm();
        //default data v dal sakte h
        model.addAttribute("userForm", userForm);
        return "register";
    }

    //processing register
    @PostMapping("/do_register")
    public String processRegister(){

        //fetch the form data
        //validate the form data
        //message successful
        //redirect to register
        return"redirect:/register";
    }

}
