package com.project.workingtime.service;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.repository.CheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CheckerService {

    private final CheckerRepository repository;

    @Autowired
    public CheckerService(CheckerRepository checkerRepository) {
        this.repository = checkerRepository;
    }

    public Checker saveCheck() {

        Optional<Checker> checker = repository.findTopByOrderByCheckinDesc();
        Checker checkerToSave = new Checker();

        if(checker.isPresent()) {
            Checker lastChecker = checker.get();
            if(lastChecker.getCheckout() == null) {
                lastChecker.setCheckout(LocalDateTime.now());
                checkerToSave = lastChecker;
            }
        }

        repository.save(checkerToSave);
        return checkerToSave;

    }

    public List<Checker> getCheckers() {
        return new ArrayList<>(repository.findAll());
    }
}
