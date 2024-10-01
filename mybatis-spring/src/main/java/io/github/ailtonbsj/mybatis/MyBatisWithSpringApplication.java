package io.github.ailtonbsj.mybatis;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.ailtonbsj.mybatis.daos.ActiveSessionDAO;
import io.github.ailtonbsj.mybatis.daos.OrganizationalUnitDAO;
import io.github.ailtonbsj.mybatis.daos.ProfileDAO;
import io.github.ailtonbsj.mybatis.daos.RoleDAO;
import io.github.ailtonbsj.mybatis.daos.UserDAO;

@MappedTypes({
	ActiveSessionDAO.class,
	OrganizationalUnitDAO.class,
	ProfileDAO.class,
	RoleDAO.class,
	UserDAO.class
})
@MapperScan("io.github.ailtonbsj.mybatis.daos")
@SpringBootApplication
public class MyBatisWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBatisWithSpringApplication.class, args);
	}

}
