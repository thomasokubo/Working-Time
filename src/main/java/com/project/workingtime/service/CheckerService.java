package com.project.workingtime.service;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.repository.CheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.workingtime.utils.DateTimeUtils.isTodaysChecker;

@Service
public class CheckerService {

    private final CheckerRepository repository;

    @Autowired
    public CheckerService(CheckerRepository checkerRepository) {
        this.repository = checkerRepository;
    }

    public Checker saveCheckIn() {

        Optional<Checker> checker = repository.findTopByOrderByCreatedTimeDesc();
        Checker checkerToSave = new Checker();

        if(checker.isPresent()) {
            Checker lastChecker = checker.get();
            if(lastChecker.getCheckout().isEmpty()) {
                lastChecker.setCheckout();
                checkerToSave = lastChecker;
            }
        }

        repository.save(checkerToSave);
        return checkerToSave;

    }

    public Optional<Checker> saveCheckOut(){

        Optional<Checker> checker = repository.findTopByOrderByCreatedTimeDesc();

        if(checker.isPresent()) {
            Checker lastChecker = checker.get();
            lastChecker.setCheckout();
            repository.save(lastChecker);
        }

        return checker;
    }

    public List<Checker> getCheckers() {
        return new ArrayList<>(repository.findAll());
    }
}
