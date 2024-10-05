package io.github.ailtonbsj.relationships.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.relationships.Utils;
import io.github.ailtonbsj.relationships.dtos.UserDTO;
import io.github.ailtonbsj.relationships.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("filter")
    @ResponseStatus(value = HttpStatus.OK)
    public Page<UserDTO> filter(
            @RequestBody UserDTO example,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String[] directions,
            @RequestParam String[] sortProps) {
        Sort sort = Utils.directionPropsToOrders(directions, sortProps);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return service.filter(example, pageable);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDTO> index() {
        return service.index();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserDTO dto) {
        return service.create(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> show(@PathVariable Long id) {
        return service.show(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return service.update(id, dto)
                .map(ent -> ResponseEntity.ok(ent))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        if (!service.destroy(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
