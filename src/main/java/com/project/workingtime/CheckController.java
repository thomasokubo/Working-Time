package com.project.workingtime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class CheckController {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @RequestMapping("/check-in")
    public String checkIn() {
        return "Check in " + dtf.format(now);
    }

    @RequestMapping("/check-out")
    public String checkOut() {
        return "Check out " + dtf.format(now);
    }
}
