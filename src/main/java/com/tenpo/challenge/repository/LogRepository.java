package com.tenpo.challenge.repository;

import com.tenpo.challenge.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
}
