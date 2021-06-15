package com.ctfcervice.authentication.repository;

import com.ctfcervice.authentication.models.InTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InTeamRepository extends JpaRepository<InTeam, Long> {

    Boolean existsByUserId(long userId);

}
