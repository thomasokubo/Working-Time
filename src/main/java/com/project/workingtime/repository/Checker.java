package com.project.workingtime.repository;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Checker {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "check_in", columnDefinition = "timestamp")
    private LocalDateTime checkin;

    @Column(name = "check_out", columnDefinition = "timestamp")
    private LocalDateTime checkout;

    public Checker() {
        checkin = LocalDateTime.now();
        checkout = null;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime localDateTime) {
        this.checkin = localDateTime;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime localDateTime) {
        this.checkout = localDateTime;
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
