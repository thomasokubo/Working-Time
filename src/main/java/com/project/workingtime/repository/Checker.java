package com.project.workingtime.repository;


import javax.persistence.*;
import java.time.LocalDateTime;

import static com.project.workingtime.utils.DateTimeUtils.localDateTimeToString;

@Entity
public class Checker {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "check_in")
    private String checkin;

    @Column(name = "check_out")
    private String checkout;

    @Column(name = "created_time", columnDefinition = "timestamp")
    private LocalDateTime createdTime;

    public Checker() {
        LocalDateTime current = LocalDateTime.now();
        checkin = localDateTimeToString(current);
        checkout = "";
        createdTime = current;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin() {
        this.checkin = localDateTimeToString(LocalDateTime.now());
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout() {
        this.checkout = localDateTimeToString(LocalDateTime.now());
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
