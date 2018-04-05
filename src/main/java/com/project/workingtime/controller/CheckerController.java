package com.project.workingtime.controller;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.repository.CheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.project.workingtime.utils.DateTimeConverter.stringToLocalDateTime;

@RestController
public class CheckerController {

    @Autowired
    private CheckerRepository repository;

    @RequestMapping(value="/check-in", method= RequestMethod.GET)
    public String checkIn() {

        // Take the last checker
        Long id = repository.count();
        Optional<Checker> checker = repository.findById(id);

        if(checker.isPresent()) {

            Checker lastChecker = checker.get();
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime lastCheckerDateTime = stringToLocalDateTime(lastChecker.getCheckin());

            // If the user didn't checkin at the current day, it'll create a new checker
            if(currentDateTime.getDayOfYear() != lastCheckerDateTime.getDayOfYear()) {
                try {
                    repository.save(new Checker());
                    return "Status: 201";
                } catch(Exception e) {
                    return "Status: 403";
                }
            // If the user already did a checkin at the current day, it's value will be updated
            } else {
                try{
                    lastChecker.setCheckin();
                    repository.save(lastChecker);
                    return "Status: 201";
                } catch(Exception e) {
                    return "Status: 403";
                }

            }

        // If there is no checker, create a new one
        } else {
            try {
                repository.save(new Checker());
                return "Status: 201";
            } catch(Exception e) {
                return "Status: 403";
            }
        }
    }


    @RequestMapping(value="check-out", method= RequestMethod.GET)
    public String checkOut() {

        Long id = repository.count();
        Optional<Checker> checker = repository.findById(id);
        if(checker.isPresent()) {
            try {
                checker.get().setCheckout();
                repository.save(checker.get());
                return "Status: 201";
            } catch(Exception e) {
                return "Status: 403";
            }
        } else {
            return "Status: 404";
        }
    }

    @RequestMapping(value="/report", method =  RequestMethod.GET)
    public List<Checker> getAllCheckers() {
        return repository.findAll();
    }

}
