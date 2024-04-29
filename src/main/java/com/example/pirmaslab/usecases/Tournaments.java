package com.example.pirmaslab.usecases;

import com.example.pirmaslab.entities.Player;
import com.example.pirmaslab.entities.Referee;
import com.example.pirmaslab.entities.Tournament;
import com.example.pirmaslab.persistence.RefereeDAO;
import com.example.pirmaslab.persistence.TournamentDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Model
public class Tournaments {

    @Inject
    private TournamentDAO tournamentDAO;
    @Inject
    private RefereeDAO refereeDAO;

    @Getter
    @Setter
    private Tournament tournamentToCreate = new Tournament();

    @Getter @Setter
    private Long refereeId = 0L;

    @Getter
    private List<Tournament> allTournaments;

    @Getter @Setter
    private List<Tournament> availableTournaments;

    @PostConstruct
    public void init(){
        this.loadAll();
    }

    @Transactional
    public void create(){
        if (refereeId != null) {
            Referee referee = this.refereeDAO.findOne(refereeId);
            tournamentToCreate.setReferee(referee);
        }

        this.tournamentDAO.persist(tournamentToCreate);
    }

    public int getNumberOfPlayersInTournament(Tournament tournament) {
        if (tournament != null)
        {
            List<Player> enrolledPlayers = tournament.getPlayers();
            return enrolledPlayers.size();
        }
        return 0;
    }

    public boolean isRegistrationOpen(Tournament tournament) {
        LocalDate currentDate = LocalDate.now();
        return tournament != null && currentDate.isAfter(tournament.getRegStartDate()) && currentDate.isBefore(tournament.getRegEndDate());
    }

    public boolean isTournamentFull(Tournament tournament) {
        return tournament != null && getNumberOfPlayersInTournament(tournament) >= tournament.getMaxPlayers();
    }

    public boolean hasParticipants(Tournament tournament) {
        return tournament != null && !tournament.getPlayers().isEmpty();
    }

    @Transactional
    public void delete(Tournament tournament) {
        if (tournament != null && tournament.getId() != null) {
            tournamentDAO.deleteById(tournament.getId());
        }
    }

    private void loadAll() {
        availableTournaments = new ArrayList<>();
        allTournaments = tournamentDAO.getAll();

        allTournaments.forEach(t -> {
            if (isRegistrationOpen(t) && !isTournamentFull(t)) {
                availableTournaments.add(t);
            }
        });
    }
}
