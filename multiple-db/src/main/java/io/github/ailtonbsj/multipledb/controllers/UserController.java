package io.github.ailtonbsj.multipledb.controllers;

import java.util.List;
import java.util.Optional;

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

import io.github.ailtonbsj.multipledb.dtos.UserDTO;
import io.github.ailtonbsj.multipledb.models.UserPK;
import io.github.ailtonbsj.multipledb.services.UserService;
import io.github.ailtonbsj.multipledb.utils.Utils;
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
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {

        return Optional.ofNullable(service.create(dto))
                .map(created -> new ResponseEntity<UserDTO>(created, HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());

    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> show(@PathVariable String id) {
        var key = Utils.stringToId(id, UserPK.class);
        return service.show(key)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @Valid @RequestBody UserDTO dto) {
        var key = Utils.stringToId(id, UserPK.class);
        return service.update(key, dto)
                .map(ent -> ResponseEntity.ok(ent))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        var key = Utils.stringToId(id, UserPK.class);
        if (!service.destroy(key))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
