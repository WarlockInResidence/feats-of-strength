package tech.dumpsterfire.featsofstrength.Repositories;

import org.springframework.stereotype.Repository;
import tech.dumpsterfire.featsofstrength.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}

