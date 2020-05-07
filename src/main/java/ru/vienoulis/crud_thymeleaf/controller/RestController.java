package ru.vienoulis.crud_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vienoulis.crud_thymeleaf.model.User;
import ru.vienoulis.crud_thymeleaf.service.interfaces.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/GET/user")
    public User getUser( Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @GetMapping("GET/user_list")
    public List<User> getAdmin() {
        return userService.getUsers();
    }
    @GetMapping("/GET/user_by_id")
    public User getNewUser(Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/POST/delete_user_by_id")
    public List<User> postDelete(Long id) {
        userService.delete(id);
        return userService.getUsers();
    }
    @PostMapping("/POST/user_update")
    public User postUpdateUser(User user){
        return user;
    }


//    @PostMapping("/POST/user_update")
//    public List<User> postUpdateUser(User user) {
//        userService.updateUser(user);
//        return userService.getUsers();
//    }
}
