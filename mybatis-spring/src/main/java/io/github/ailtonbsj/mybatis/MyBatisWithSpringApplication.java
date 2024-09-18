package io.github.ailtonbsj.mybatis;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.ailtonbsj.mybatis.models.User;

@MappedTypes(User.class)
@MapperScan("io.github.ailtonbsj.mybatis.mappers")
@SpringBootApplication
public class MyBatisWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBatisWithSpringApplication.class, args);
	}

}
