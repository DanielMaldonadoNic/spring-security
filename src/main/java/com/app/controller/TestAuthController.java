package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "Hello World!";
    }



    @GetMapping("/hello-security")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloSecurity(){
        return "Hello World!";
    }
}
