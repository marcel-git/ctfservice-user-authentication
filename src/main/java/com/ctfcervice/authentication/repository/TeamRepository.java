package com.ctfcervice.authentication.repository;

import com.ctfcervice.authentication.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Teams, Long> {

}
