package com.example.pirmaslab.services;

import com.example.pirmaslab.entities.Player;
import com.example.pirmaslab.entities.Tournament;
import com.example.pirmaslab.persistence.GenericDAO;
import com.example.pirmaslab.persistence.TournamentDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Decorator
@Dependent
public abstract class TournamentEngagementCountDecorator implements GenericDAO<Player> {
    @Inject
    @Delegate
    private GenericDAO<Player> playerDAO;

    @Inject
    private TournamentDAO tournamentDAO;

    @Override
    @Transactional
    public void persist(Player player) {
        playerDAO.persist(player);
        List<Tournament> tournaments = player.getTournaments();
        tournaments.forEach(t -> {
            Tournament existingTournament = tournamentDAO.findOne(t.getId());
            if (existingTournament.getTournamentEngagementCount() == null) {
                existingTournament.setTournamentEngagementCount(0);
            }
            existingTournament.setTournamentEngagementCount(existingTournament.getTournamentEngagementCount() + 1);
            tournamentDAO.update(existingTournament);
        });
    }
}
