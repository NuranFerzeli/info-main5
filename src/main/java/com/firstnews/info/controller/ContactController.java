package com.firstnews.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {
    @RequestMapping(method = RequestMethod.GET , value = "/contact")
    public String getLogin(){
        return "contact.html";
    }
}
