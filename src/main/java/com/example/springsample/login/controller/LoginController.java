package com.example.springsample.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class LoginController {
    
    // ログイン画面のGET用コントローラー
    @GetMapping("/login")
    public String getLogin(Model model) {
    
        // login.htmlに画面遷移
        return "login/login";
    }

    // ログイン画面のPOST用コントローラー
    @PostMapping("/login")
    public String putLogin(Model model) {
    
        // ホーム画面に遷移(SecurityConfigのformLogin処理 defaultSuccessUrl優先)
        return "redirect:/home";
    }
    
}
