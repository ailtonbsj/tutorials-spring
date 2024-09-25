CREATE TABLE public.organizational_unit (
	id          bigserial PRIMARY KEY,
	name        varchar(255) NULL,
	parent_unit int8 NULL REFERENCES public.organizational_unit(id)
);

CREATE TABLE public.profiles (
	id         bigserial PRIMARY KEY,
	avatar_url varchar(255) NULL,
	biography  varchar(255) NULL,
	birthday   date NULL,
	country    varchar(255) NULL,
	instagram  varchar(255) NULL,
	name       varchar(255) NULL,
	phone      int8 NULL,
	salary     float8 NULL
);

CREATE TABLE public.users (
	id            bigserial PRIMARY KEY,
    username      varchar(255) NOT NULL,
	password      varchar(255) NOT NULL,
	is_active     bool NOT NULL DEFAULT TRUE,
	department_id int8 NULL REFERENCES public.organizational_unit(id),
	profile_id    int8 NULL UNIQUE REFERENCES public.profiles(id),
    created_at    timestamp(6) NULL
);

CREATE TABLE public.roles (
	id   bigserial PRIMARY KEY,
	name varchar(255) NULL
);

CREATE TABLE public.permissions (
	user_id int8 REFERENCES public.users(id),
	role_id int8 REFERENCES public.roles(id),
    PRIMARY KEY(user_id, role_id)
);

CREATE TABLE public.active_session (
	id        bigserial PRIMARY KEY,
	agent     varchar(255) NULL,
	device    varchar(255) NULL,
	signed_in timestamp(6) NULL,
	user_id   int8 NULL REFERENCES public.users(id)
);
