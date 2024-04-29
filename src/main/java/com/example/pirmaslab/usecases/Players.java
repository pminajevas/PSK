package com.example.pirmaslab.usecases;

import com.example.pirmaslab.entities.Tournament;
import com.example.pirmaslab.entities.Player;
import com.example.pirmaslab.persistence.PlayerDAO;
import com.example.pirmaslab.persistence.TournamentDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class Players {

    @Inject
    private PlayerDAO playerDAO;
    @Inject
    private TournamentDAO tournamentDAO;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @Getter @Setter
    private List<Long> selectedTournaments;

    @Getter @Setter
    private Integer howManyInTournament = 0;

    @Getter @Setter
    private List<Player> allPlayers;

    @PostConstruct
    public void init(){
        loadAll();
    }

    @Transactional
    public void create(){
        List<Tournament> tournaments = new ArrayList<>();
        selectedTournaments.forEach(id -> {
            tournaments.add(tournamentDAO.findOne(id));
        });

        playerToCreate.setTournaments(tournaments);

        this.playerDAO.persist(playerToCreate);
    }

    @Transactional
    public void delete(Player player) {
        if (player != null && player.getId() != null) {
            playerDAO.deleteById(player.getId());
        }
    }

    @Transactional
    public void leave(Player player, Tournament tournament) {
        if (player != null && tournament != null) {
            List<Tournament> tournaments = player.getTournaments();
            tournaments.remove(tournament);
            player.setTournaments(tournaments);
            playerDAO.update(player);
        }
    }

    private void loadAll(){
        this.allPlayers = playerDAO.getAll();
    }
}
