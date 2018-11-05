package ru.study.simpleshop.dao;

import ru.study.simpleshop.models.Role;
import ru.study.simpleshop.models.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    User findByUsername(String username);

    User findByEmail(String email);

    void setRole(User user, Role role);

    void save(User user);
}
