package com.goit17.testApp.template.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/api/test")
    public String test() {
        return "test";
    }
}