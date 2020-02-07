package com.wangpin.bbs.userManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultPageController {

    @GetMapping(value = "/")
    public String defaultPath(Model model) {
        return "redirect:/userManage/main";
    }
  }
