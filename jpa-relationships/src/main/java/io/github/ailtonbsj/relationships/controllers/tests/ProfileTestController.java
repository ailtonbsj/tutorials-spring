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

import io.github.ailtonbsj.relationships.dtos.ProfileDTO;
import io.github.ailtonbsj.relationships.services.ProfileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("test/profiles")
@RequiredArgsConstructor
public class ProfileTestController {

    final ProfileService service;

    @GetMapping("populate")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void populate() {
        Faker faker = new Faker(Locale.of("pt-BR"), new RandomService());
        List<ProfileDTO> list = Instancio.ofList(ProfileDTO.class).size(50)
                .supply(Select.field(ProfileDTO::getName), faker.name()::fullName)
                .supply(Select.field(ProfileDTO::getBiography), faker.name()::title)
                .create();
        list.forEach(service::create);
    }

}
