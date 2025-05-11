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
