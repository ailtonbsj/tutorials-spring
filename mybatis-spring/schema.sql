CREATE DATABASE IF NOT EXISTS mybatis_spring;
USE mybatis_spring;

CREATE TABLE IF NOT EXISTS organizational_unit (
	id          int8 PRIMARY KEY auto_increment,
	name        varchar(255) NULL,
	parent_unit int8 NULL REFERENCES organizational_unit(id)
);

CREATE TABLE IF NOT EXISTS profiles (
	id         int8 PRIMARY KEY auto_increment,
	avatar_url varchar(255) NULL,
	biography  varchar(255) NULL,
	birthday   date NULL,
	country    varchar(255) NULL,
	instagram  varchar(255) NULL,
	name       varchar(255) NULL,
	phone      int8 NULL,
	salary     float8 NULL
);

CREATE TABLE IF NOT EXISTS users (
	id            int8 PRIMARY KEY auto_increment,
    username      varchar(255) NOT NULL,
	password      varchar(255) NOT NULL,
	is_active     bool NOT NULL DEFAULT TRUE,
	department_id int8 NULL REFERENCES organizational_unit(id),
	profile_id    int8 NULL UNIQUE REFERENCES profiles(id),
    created_at    timestamp(6) NULL
);

CREATE TABLE IF NOT EXISTS roles (
	id   int8 PRIMARY KEY auto_increment,
	name varchar(255) NULL
);

CREATE TABLE IF NOT EXISTS permissions (
	user_id int8 REFERENCES users(id),
	role_id int8 REFERENCES roles(id),
    PRIMARY KEY(user_id, role_id)
);

CREATE TABLE IF NOT EXISTS active_session (
	id        int8 PRIMARY KEY auto_increment,
	agent     varchar(255) NULL,
	device    varchar(255) NULL,
	signed_in timestamp(6) NULL,
	user_id   int8 NULL REFERENCES users(id)
);
