package io.github.ailtonbsj.multipledb.repositories.oracle;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.multipledb.models.oracle.ActiveSession;
import io.github.ailtonbsj.multipledb.models.oracle.ActiveSessionPK;

public interface ActiveSessionRepository extends JpaRepository<ActiveSession, ActiveSessionPK> {
}
