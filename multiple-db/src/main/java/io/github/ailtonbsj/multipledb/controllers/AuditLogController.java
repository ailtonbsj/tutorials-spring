package io.github.ailtonbsj.multipledb.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.multipledb.models.postgres.AuditLog;
import io.github.ailtonbsj.multipledb.repositories.postgres.AuditLogRepository;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    final AuditLogRepository repository;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuditLog> index() {
        return repository.findAll();
    }
    
}
