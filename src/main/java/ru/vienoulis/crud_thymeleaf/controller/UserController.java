package ru.vienoulis.crud_thymeleaf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vienoulis.crud_thymeleaf.model.User;
import ru.vienoulis.crud_thymeleaf.service.interfaces.RoleService;
import ru.vienoulis.crud_thymeleaf.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/**")
    public String getAll() {
        return "redirect:/login";
    }

    @GetMapping(value = "user")
    public String getUser(ModelMap map, Authentication authentication) {
        User visitor = (User) authentication.getPrincipal();
        List<User> users= new ArrayList<>();
        users.add(visitor);
        map.addAttribute("users", users);
        map.addAttribute("visitor", visitor);
        return "user";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAdmin(ModelMap map, Authentication authentication) {
        List<User> users = userService.getUsers();
        User user = (User) authentication.getPrincipal();
        map.addAttribute("visitor",user);
        map.addAttribute("users", users);
        map.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/admin/user")
    public String getNewUser(ModelMap map, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        map.addAttribute("visitor",user);

        return "newuser";
    }

    @PostMapping("/admin/user")
    public String postNewUser(ModelMap map, Authentication authentication, User user, String ...roleList) {
        userService.add(user, roleList);
        User visitor = (User) authentication.getPrincipal();
        map.addAttribute("visitor",visitor);

        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String postDelete(String id, ModelMap map) {
        userService.delete(id);
        map.addAttribute("users", userService.getUsers());

        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String getUpdate(Long userId, ModelMap map) {
        User user = userService.getUserById(userId);
        map.addAttribute("userUpdated", user);
        map.addAttribute("roles", roleService.getAllRoles());
        return "update";
    }

    @PostMapping("/admin/update")
    public String postUpdate(User user, String... roleList) {
        userService.updateUser(user, roleList);
        return "redirect:/admin";
    }
}