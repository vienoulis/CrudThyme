package ru.vienoulis.crud_thymeleaf.service;

import ru.vienoulis.crud_thymeleaf.dao.interfaces.RoleDao;
import ru.vienoulis.crud_thymeleaf.dao.interfaces.UserDao;
import ru.vienoulis.crud_thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vienoulis.crud_thymeleaf.service.interfaces.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDao dao;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return dao.getAllUsers();
    }

    @Override
    @Transactional
    public void add(User user, String[] roleList) {
        user.setRoleSet(Arrays.stream(roleList)
                .map(x->roleDao.getRoleByName(x))
                .collect(Collectors.toSet())
        );
        dao.addUser(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }


    @Override
    @Transactional
    public void updateUser (User user, String...roleList){
        user.setRoleSet(Arrays.stream(roleList)
                .map(x->roleDao.getRoleByName(x))
                .collect(Collectors.toSet())
        );
        dao.update(user);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
            dao.delete(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByName(username);
    }
}
