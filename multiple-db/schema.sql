--- Oracle 11g Schema

ALTER USER MULTIDB quota unlimited on SYSTEM;
GRANT CREATE SESSION TO MULTIDB;

CREATE TABLE MULTIDB.USERS (    
    username      VARCHAR2(255) NOT NULL,
    created_at    DATE NOT NULL,
    password      VARCHAR2(255) NOT NULL,
    is_active     NUMBER(1) DEFAULT 1 NOT NULL,
    department_id NUMBER(19) NULL,
    profile_id    NUMBER(19) NULL,
    CONSTRAINT pk_users PRIMARY KEY (username, created_at)
);

CREATE TABLE MULTIDB.ACTIVE_SESSION (
    user_created_at  DATE NOT NULL,
	device           VARCHAR2(255) NULL,
	agent            VARCHAR2(255) NULL,
	signed_in        DATE NULL,
    user_id       VARCHAR2(255) NOT NULL,
	CONSTRAINT pk_sessions PRIMARY KEY (user_id, user_created_at, device),
	CONSTRAINT fk_users_session FOREIGN KEY (user_id, user_created_at) REFERENCES MULTIDB.USERS(username, created_at)
);

--- PostgreSQL Schema

CREATE TABLE public.audit_logs (
    id            bigserial PRIMARY KEY,
    user_id       varchar(255) NOT NULL,
    action        varchar(255) NOT NULL,
    timestamp     timestamp(6) NOT NULL DEFAULT now()
);
