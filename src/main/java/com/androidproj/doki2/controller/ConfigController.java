package com.androidproj.doki2.controller;

import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@Controller
public class ConfigController {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public ConfigController() {
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping("/success")
    public String loginSuccess(){
        return "登陆成功";
    }
    @RequestMapping("/fail")
    public String loginFail(){
        return "登陆失败";
    }

    @RequestMapping({"/signIn"})
    public String signIn() {
        return "SignIn";
    }

    @RequestMapping({"/signOut"})
    public String signOut() {
        return "SignOut";
    }

    @RequestMapping({"/getPassword"})
    public String getPassword(@RequestParam(value = "password",required = true) String password) {
        String tmp = this.bCryptPasswordEncoder.encode(password);
        return tmp;
    }
}
