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

        Long id = repository.count();
        Optional<Checker> checker = repository.findById(id);
        Checker checkerToSave = new Checker();

        if(checker.isPresent()) {
            Checker lastChecker = checker.get();
            if(isTodaysChecker(lastChecker)) {
                lastChecker.setCheckin();
                checkerToSave = lastChecker;
            }
        }

        repository.save(checkerToSave);
        return checkerToSave;

    }

    public Optional<Checker> saveCheckOut(){

        Long id = repository.count();
        Optional<Checker> checker = repository.findById(id);

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
