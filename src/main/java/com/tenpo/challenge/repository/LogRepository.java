package com.tenpo.challenge.repository;

import com.tenpo.challenge.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
    @Async
    <S extends Log> S save(S log);

    Page<Log> findByEndpointContaining(String endpoint, Pageable pageable);
}
