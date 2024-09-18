package io.github.ailtonbsj.mybatis.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.mybatis.mappers.UserDAO;
import io.github.ailtonbsj.mybatis.models.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDAO userDAO;

    /* Bulk data */

    @PostMapping("bulk")
    public void create(@RequestBody List<User> users) {
        users.forEach(user -> userDAO.insert(user));
    }

    @GetMapping("filter")
    public List<User> getByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double salary,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sort) {
        User user = new User(null, "%" + name + "%", salary);
        return userDAO.filter(user, pageNumber * pageSize, pageSize, sort);
    }

    @DeleteMapping("bulk")
    public void delete(@RequestBody List<Long> ids) {
        ids.forEach(id -> userDAO.delete(id));
    }

    /* Few data */

    @PostMapping
    public void create(@RequestBody User user) {
        userDAO.insert(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userDAO.select();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userDAO.selectById(id);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userDAO.update(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userDAO.delete(id);
    }

}
