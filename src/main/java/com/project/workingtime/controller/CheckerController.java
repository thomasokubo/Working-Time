package com.project.workingtime.controller;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.repository.CheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CheckerController {

    @Autowired
    private CheckerRepository repository;

    @RequestMapping(value="check-in", method= RequestMethod.POST)
    public String checkIn() {

        try {
            repository.save(new Checker());
            return "Status: 201";
        } catch(Exception e) {
            return "Status: 403";
        }

    }


    @RequestMapping(value="check-out", method= RequestMethod.POST)
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

}
