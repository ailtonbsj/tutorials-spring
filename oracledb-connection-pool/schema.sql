--- Oracle 11g Schema

CREATE USER studyuser IDENTIFIED BY 123;
ALTER USER studyuser quota unlimited on USERS;
ALTER USER studyuser quota unlimited on SYSTEM;
GRANT CREATE SESSION TO studyuser;

CREATE TABLE studyuser.users (
    username      VARCHAR2(255)       NOT NULL,
    created_at    DATE                NOT NULL,
    password      VARCHAR2(255)       NOT NULL,
    is_active     NUMBER(1) DEFAULT 1 NOT NULL,
    department_id NUMBER(19)          NULL,
    profile_id    NUMBER(19)          NULL,

    CONSTRAINT pk_users PRIMARY KEY (username, created_at)
);

CREATE TABLE studyuser.active_sessions (
    user_username   VARCHAR2(255) NOT NULL,
    user_created_at DATE          NOT NULL,
	device          VARCHAR2(255) NULL,
	agent           VARCHAR2(255) NULL,
	signed_in       DATE          NULL,

	CONSTRAINT pk_sessions PRIMARY KEY (user_username, user_created_at, device),
	CONSTRAINT fk_users_session FOREIGN KEY (user_username, user_created_at) REFERENCES studyuser.users(username, created_at)
);