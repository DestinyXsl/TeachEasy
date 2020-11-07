package com.homework.teach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String index(Model model)
    {

        return "zhuYiIndex";
    }
    @RequestMapping(value = "/newPage")
    public String newPage(Model model)
    {
        return "newPage";
    }
    @RequestMapping(value = "/template")
    public String commonFrame(Model model)
    {

        return "contentTemplate";
    }

}