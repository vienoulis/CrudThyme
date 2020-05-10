package ru.vienoulis.crud_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vienoulis.crud_thymeleaf.model.Role;
import ru.vienoulis.crud_thymeleaf.model.User;
import ru.vienoulis.crud_thymeleaf.service.interfaces.RoleService;
import ru.vienoulis.crud_thymeleaf.service.interfaces.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/GET/user")
    public User getUser( Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @GetMapping("/GET/user/by_id")
    public User getUserById( Long id){
        return userService.getUserById(id);
    }

    @GetMapping("GET/user_list")
    public List<User> getAdmin() {
        return userService.getUsers();
    }

    @PostMapping("/POST/delete")
    public void postDelete(Long id) {
        userService.delete(id);
    }
    @PostMapping("/POST/update")
    public void postUpdateUser(User user, String ...roleList){
        userService.updateUser(user, roleList);
    }

    @GetMapping("/GET/role/list")
    public List<Role> gepRoleList(){
        return roleService.getAllRoles();
    }
}
