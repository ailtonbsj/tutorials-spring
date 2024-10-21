package io.github.ailtonbsj.multipledb.daos.postgres;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import io.github.ailtonbsj.multipledb.models.postgres.AuditLog;

@Mapper
public interface AuditLogDAO {

    @Select("select * from public.audit_logs")
    @Results(id = "auditLogResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "action", column = "action"),
            @Result(property = "timestamp", column = "timestamp")
    })
    List<AuditLog> findAll();

}
