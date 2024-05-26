package com.example.pirmaslab.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
@Alternative
public class TournamentDurationInDateTimeEstimator implements TournamentDurationEstimator{
    @Override
    public String estimateTournamentDuration() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            //
        }

        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 59);

        return LocalTime.of(hour, minute).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
