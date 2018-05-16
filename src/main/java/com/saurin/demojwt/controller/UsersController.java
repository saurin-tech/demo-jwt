package com.saurin.demojwt.controller;

import com.saurin.demojwt.service.AccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;

    @RequestMapping(value="/getallusers", method=RequestMethod.GET)
    public List listUser(){
        return accountUserDetailsService.findAllUsers();
    }
}
