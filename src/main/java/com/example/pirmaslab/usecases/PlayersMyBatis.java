package com.example.pirmaslab.usecases;

import com.example.pirmaslab.mybatis.dao.PlayerMapper;
import com.example.pirmaslab.mybatis.dao.PlayerTournamentMapper;
import com.example.pirmaslab.mybatis.model.Player;
import com.example.pirmaslab.mybatis.model.PlayerTournament;
import com.example.pirmaslab.mybatis.model.Tournament;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PlayersMyBatis {

    @Inject
    private PlayerMapper playerMapper;
    @Inject
    private PlayerTournamentMapper playerTournamentMapper;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @Getter @Setter
    private List<Long> selectedTournaments;

    @Getter @Setter
    private Integer howManyInTournament = 0;

    @Getter @Setter
    private List<Player> allPlayers;

    @PostConstruct
    public void init() {
        loadAll();
    }

    @Transactional
    public void create() {
        this.playerMapper.insert(playerToCreate);

        selectedTournaments.forEach(id -> {
            PlayerTournament playerTournament = new PlayerTournament();
            playerTournament.setPlayersId(playerToCreate.getId());
            playerTournament.setTournamentsId(id);

            playerTournamentMapper.insert(playerTournament);
        });
    }

    @Transactional
    public void delete(Player player) {
        if (player != null && player.getId() != null) {
            List<PlayerTournament> playerTournaments;

            playerTournaments = playerTournamentMapper.selectByPlayer(player.getId());
            playerTournaments.forEach(pt -> {
                playerTournamentMapper.delete(pt);
            });

            playerMapper.deleteByPrimaryKey(player.getId());
        }
    }

    @Transactional
    public void leave(Player player, Tournament tournament) {
        if (player != null && tournament != null) {
            PlayerTournament pt = playerTournamentMapper.selectByCompositeKey(player.getId(), tournament.getId());
            playerTournamentMapper.delete(pt);
        }
    }

    private void loadAll() {
        this.allPlayers = playerMapper.selectAll();
    }
}
