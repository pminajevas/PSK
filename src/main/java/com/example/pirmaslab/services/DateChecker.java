package com.example.pirmaslab.services;

import javax.enterprise.context.RequestScoped;
import java.time.LocalDate;

@RequestScoped
public class DateChecker {
    private final LocalDate currentDate = LocalDate.now();

    public boolean currentDateIsBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return currentDate.isAfter(dateFrom) && currentDate.isBefore(dateTo);
    }
}
