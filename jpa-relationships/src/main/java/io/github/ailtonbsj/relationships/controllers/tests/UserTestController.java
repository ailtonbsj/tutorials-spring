package io.github.ailtonbsj.relationships.controllers.tests;

import java.util.List;
import java.util.Locale;

import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;

import io.github.ailtonbsj.relationships.dtos.UserDTO;
import io.github.ailtonbsj.relationships.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("test/users")
@RequiredArgsConstructor
public class UserTestController {

    final UserService service;

    @GetMapping("populate")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void populate() {
        Faker faker = new Faker(Locale.of("pt-BR"), new RandomService());
        List<UserDTO> list = Instancio.ofList(UserDTO.class).size(50)
                .supply(Select.field(UserDTO::getUsername), faker.name()::username)
                .set(Select.field(UserDTO::getDepartmentId), null)
                .set(Select.field(UserDTO::getProfileId), null)
                .set(Select.field(UserDTO::getRolesId), null)
                .create();
        list.forEach(service::create);
    }

}
