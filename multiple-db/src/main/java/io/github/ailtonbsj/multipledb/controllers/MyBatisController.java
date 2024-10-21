package io.github.ailtonbsj.multipledb.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ailtonbsj.multipledb.daos.oracle.UserDAO;
import io.github.ailtonbsj.multipledb.daos.postgres.AuditLogDAO;
import io.github.ailtonbsj.multipledb.models.oracle.User;
import io.github.ailtonbsj.multipledb.models.postgres.AuditLog;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MyBatisController {

    private final UserDAO userDao;
    private final AuditLogDAO auditLogDao;

    @GetMapping("users-mybatis")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @GetMapping("audit-logs-mybatis")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuditLog> listAuditLogs() {
        return auditLogDao.findAll();
    }

}
