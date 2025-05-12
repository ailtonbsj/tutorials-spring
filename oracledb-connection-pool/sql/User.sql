create table studyuser.tb_users_ng (

    ci_users_ng	serial	not null	primary key,
    username	varchar(255)	 not null	,	-- username
    created_at	timestamp	 not null	,	-- created_at
    password	varchar(255)	 not null	,	-- password
    is_active	integer	 not null	,	-- is_active
    department_id	bigint	 null	,	-- department_id
    profile_id	bigint	 null	,	-- profile_id

-- TODO: constraint uk_primary_key unique (key1, key2, key3)
);

comment on column studyuser.tb_users_ng.username is 'users.username';
comment on column studyuser.tb_users_ng.created_at is 'users.created_at';
comment on column studyuser.tb_users_ng.password is 'users.password';
comment on column studyuser.tb_users_ng.is_active is 'users.is_active';
comment on column studyuser.tb_users_ng.department_id is 'users.department_id';
comment on column studyuser.tb_users_ng.profile_id is 'users.profile_id';
