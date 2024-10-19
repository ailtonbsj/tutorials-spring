package io.github.ailtonbsj.multipledb.controllers.tests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.multipledb.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("test/users")
@RequiredArgsConstructor
public class UserTestController {

    final UserService service;

    @GetMapping("populate")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void populate() {
        // Faker faker = new Faker(Locale.of("pt-BR"), new RandomService());
        // List<UserDTO> list = Instancio.ofList(UserDTO.class).size(50)
        //         .supply(Select.field(UserDTO::getUsername), faker.name()::username)
        //         .set(Select.field(UserDTO::getDepartmentId), null)
        //         .set(Select.field(UserDTO::getProfileId), null)
        //         .set(Select.field(UserDTO::getRolesId), null)
        //         .create();
        // list.forEach(service::create);
    }

}
