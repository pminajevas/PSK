package com.example.pirmaslab.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Specializes
@ApplicationScoped
public class TournamentDetailedDurationInDateTimeEstimator extends TournamentDurationInDateTimeEstimator{
    @Override
    public String estimateTournamentDuration() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            //
        }

        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 59);
        int seconds = ThreadLocalRandom.current().nextInt(0, 59);

        return LocalTime.of(hour, minute, seconds).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
