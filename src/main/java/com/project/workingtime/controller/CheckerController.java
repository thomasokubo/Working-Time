package com.project.workingtime.controller;

import com.project.workingtime.repository.Checker;
import com.project.workingtime.service.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value="/check-in", method= RequestMethod.GET)
    public String checkIn() {
        service.saveCheckIn();
        return "200";
    }


    @RequestMapping(value="check-out", method= RequestMethod.GET)
    public String checkOut() {
        service.saveCheckOut();
        return "200";
    }

    @RequestMapping(value="/report", method =  RequestMethod.GET)
    public List<Checker> getAllCheckers() {
        return service.getCheckers();
    }

}
