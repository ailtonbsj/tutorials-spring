package io.github.ailtonbsj.relationships.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.relationships.dtos.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserDTO user) {
        System.out.println(user);
    }

}
