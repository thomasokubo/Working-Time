package com.project.workingtime.controller;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.service.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CheckerController {


    private CheckerService service;

    @Autowired
    public CheckerController(CheckerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/check-in", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Checker postCheckIn() {
        return service.saveCheckIn();
    }

    @RequestMapping(value = "check-out", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Optional<Checker> postCheckOut() {
        return service.saveCheckOut();
    }

    @RequestMapping(value = "/report", method =  RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Checker> getCheckers() {
        return service.getCheckers();
    }
}
