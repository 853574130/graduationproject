package org.iauhsoaix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {

    @RequestMapping("/hello2")
    public String hello(){
        System.out.print("666");
        System.out.print("777");
        return "Hello Word SpringBoot";
    }
}
