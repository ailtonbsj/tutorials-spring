package io.github.ailtonbsj.multipledb.repositories.oracle;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.multipledb.models.oracle.User;
import io.github.ailtonbsj.multipledb.models.oracle.UserPK;

public interface UserRepository extends JpaRepository<User, UserPK> {
}
