package br.ailtonbsj.studies.oracledb_cp.repository;

import br.ailtonbsj.studies.oracledb_cp.domain.ActiveSession;
import br.ailtonbsj.studies.oracledb_cp.domain.key.ActiveSessionPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveSessionRepository extends JpaRepository<ActiveSession, ActiveSessionPK> {
}
