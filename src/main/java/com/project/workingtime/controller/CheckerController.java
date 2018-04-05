package com.project.workingtime.controller;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.service.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckerController {


    private CheckerService service;

    @Autowired
    public CheckerController(CheckerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/check-in", method = RequestMethod.POST)
    public ResponseEntity postCheckIn() {
        service.saveCheckIn();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "check-out", method = RequestMethod.POST)
    public ResponseEntity postCheckOut() {
        service.saveCheckOut();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method =  RequestMethod.GET)
    public List<Checker> getCheckers() {
        return service.getCheckers();
    }
}
