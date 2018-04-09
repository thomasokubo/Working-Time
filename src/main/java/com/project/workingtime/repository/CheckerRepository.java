package com.project.workingtime.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CheckerRepository extends CrudRepository<Checker, Long>{

    List<Checker> findAll();

    Optional<Checker> findById(Long id);

    Optional<Checker> findTopByOrderByCheckinDesc();
}
