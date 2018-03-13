package com.project.workingtime.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Checker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String checkin;

    private String checkout;

    public Checker() {
        checkin = LocalDateTime.now().toString();
        checkout = "";
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin() {
        this.checkin = LocalDateTime.now().toString();
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout() {
        this.checkout = LocalDateTime.now().toString();
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
