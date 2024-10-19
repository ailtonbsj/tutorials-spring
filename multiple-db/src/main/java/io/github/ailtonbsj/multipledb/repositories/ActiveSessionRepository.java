package io.github.ailtonbsj.multipledb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ailtonbsj.multipledb.models.ActiveSession;
import io.github.ailtonbsj.multipledb.models.ActiveSessionPK;


public interface ActiveSessionRepository extends JpaRepository<ActiveSession, ActiveSessionPK> {
}
