package io.github.ailtonbsj.multipledb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.multipledb.models.User;
import io.github.ailtonbsj.multipledb.models.UserPK;

public interface UserRepository extends JpaRepository<User, UserPK> {
}
