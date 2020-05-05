package ru.vienoulis.crud_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vienoulis.crud_thymeleaf.model.User;
import ru.vienoulis.crud_thymeleaf.service.interfaces.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserService service;

    @GetMapping("/test")
    public List<User> getAllUser(){
        return service.getUsers();
    }

}
