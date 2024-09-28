package io.github.ailtonbsj.relationships.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.relationships.dtos.ProfileDTO;
import io.github.ailtonbsj.relationships.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProfileDTO> index() {
        return service.index();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProfileDTO create(@Valid @RequestBody ProfileDTO dto) {
        return service.create(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileDTO> show(@PathVariable Long id) {
        return service.show(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable Long id,
            @Valid @RequestBody ProfileDTO dto) {
        return service.update(id, dto)
                .map(ent -> ResponseEntity.ok(ent))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        if(!service.destroy(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
