package br.ailtonbsj.studies.oracledb_cp.repository;

import br.ailtonbsj.studies.oracledb_cp.domain.User;
import br.ailtonbsj.studies.oracledb_cp.domain.key.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserPK> {
}
