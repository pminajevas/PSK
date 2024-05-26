package com.example.pirmaslab.usecases;

import com.example.pirmaslab.interceptors.Duration;
import com.example.pirmaslab.services.TournamentDurationEstimator;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Model
@SessionScoped
@Duration
public class TournamentLengthEstimator implements Serializable {
    @Inject
    private TournamentDurationEstimator durationEstimator;

    private final Map<Long, CompletableFuture<String>> durationEstimatorTasks = new HashMap<>();

    public String estimateDuration(Long tournamentId) {
        this.durationEstimatorTasks.put(tournamentId, CompletableFuture.supplyAsync(() -> durationEstimator.estimateTournamentDuration()));
        return this.refreshStatus();
    }

    public String refreshStatus() {
        return "/index?&faces-redirect=true";
    }

    public String getDurationEstimationStatus(Long tournamentId) throws ExecutionException, InterruptedException {
        CompletableFuture<String> task = durationEstimatorTasks.get(tournamentId);

        if (task == null) {
            return "No estimation started, click Estimate to start.";
        } else if (!task.isDone()) {
            return "Estimation in-progress";
        } else {
            return "Estimated tournament duration: " + task.get();
        }
    }

    public Boolean shouldDisplayUpdate(Long tournamentId) {
        CompletableFuture<String> task = durationEstimatorTasks.get(tournamentId);
        if (task == null) return false;
        return !task.isDone();
    }
}
