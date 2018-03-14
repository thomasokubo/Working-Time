package com.project.workingtime.repository;

import com.project.workingtime.service.CheckerService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Checker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String checkin;

    private String checkout;


    public Checker() {
        checkin = CheckerService.localDateTimeToString(LocalDateTime.now());
        checkout = "";

    }


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin() {
        this.checkin = CheckerService.localDateTimeToString(LocalDateTime.now());
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout() {
        this.checkout = CheckerService.localDateTimeToString(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return String.format("Checker:\n" +
                "{ id = %d\n" +
                "Checkin = %s\n" +
                "Checkout = %s\n"
        );
    }
}
