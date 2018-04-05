package com.project.workingtime.service;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.repository.CheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.workingtime.utils.DateTimeConverter.stringToLocalDateTime;

@Service
public class CheckerService {

    private final CheckerRepository repository;

    @Autowired
    public CheckerService(CheckerRepository checkerRepository) {
        this.repository = checkerRepository;
    }

    public String saveCheckIn() {
        // Take the last checker
        Long id = repository.count();
        Optional<Checker> checker = repository.findById(id);

        if(checker.isPresent()) {

            Checker lastChecker = checker.get();
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime lastCheckerDateTime = stringToLocalDateTime(lastChecker.getCheckin());

            // If the user didn't checkin at the current day, it'll create a new checker
            if(currentDateTime.getDayOfYear() != lastCheckerDateTime.getDayOfYear()) {
                repository.save(new Checker());
                return "Status: 201";
            // If the user already did a checkin at the current day, it's value will be updated
            } else {
                lastChecker.setCheckin();
                repository.save(lastChecker);
                return "Status: 201";

            }

        // If there is no checker, create a new one
        } else {
            repository.save(new Checker());
            return "Status: 201";
        }
    }


    public String saveCheckOut(){
        Long id = repository.count();
        Optional<Checker> checker = repository.findById(id);
        if(checker.isPresent()) {

            checker.get().setCheckout();
            repository.save(checker.get());
            return "Status: 201";

        } else {
            return "Status: 404";
        }
    }

    public List<Checker> getCheckers() {
        return new ArrayList<>(repository.findAll());
    }
}
