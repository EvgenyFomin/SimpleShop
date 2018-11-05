package ru.study.simpleshop.dao.impls.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.study.simpleshop.dao.UserDAO;
import ru.study.simpleshop.models.Role;
import ru.study.simpleshop.models.User;

import java.util.Collections;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.query("select * from users where username = ?",
                new Object[]{username},
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.query("select * from users where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public void setRole(User user, Role role) {
        user.setRoles(Collections.singleton(role));
        jdbcTemplate.update("insert into user_role (user_id, role) select id, ? from users where username = ?",
                role.name(), user.getUsername());
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (username, email, password) VALUES (?, ?, ?)",
                user.getUsername(), user.getEmail(), user.getPassword());
    }
}
