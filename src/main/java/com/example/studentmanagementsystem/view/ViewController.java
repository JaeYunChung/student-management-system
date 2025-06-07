package com.example.studentmanagementsystem.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/home")
    public String getHomeView(){
        return "home";
    }
    @GetMapping("/login")
    public String getLoginView(){
        return "login";
    }
    @GetMapping("/signup")
    public String getSignUpView(){
        return "signup";
    }

    @GetMapping("/dormitory")
    public String getDormitoryView(){
        return "dormitory";
    }

    @GetMapping("/library")
    public String getLibraryView(){
        return "library";
    }
}
