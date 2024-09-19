package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

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
    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session){  //modelAttribute does is it matches the name with that in userform and that in the register to store corresponding
        System.out.println(userForm);
//        User user=User.builder()
//                .name(userForm.getName())
//                .password(userForm.getPassword())
//                .email(userForm.getEmail())
//                .about(userForm.getDescription())
//                .profilePic("static/images/1048276-200.png")
//                .build();
        User user = new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getDescription());
        user.setProfilePic("static/images/1048276-200.png");
        User userSaved= userService.saveUser(user);
        System.out.println(userSaved);

//        to give the message
        Message message=Message.builder().content("Registered Successfully").type(MessageType.green).build();
        session.setAttribute("message", message);

        return"redirect:/register";
    }

}
