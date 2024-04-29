package com.example.pirmaslab.usecases;

import com.example.pirmaslab.entities.Referee;
import com.example.pirmaslab.persistence.RefereeDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Referees {

    @Inject
    private RefereeDAO refereeDAO;

    @Getter
    @Setter
    private Referee refereeToCreate = new Referee();

    @Getter
    private List<Referee> allReferees;

    @PostConstruct
    public void init(){
        loadAll();
    }

    @Transactional
    public void create(){
        this.refereeDAO.persist(refereeToCreate);
    }

    public boolean hasTournaments(Referee referee) {
        return referee != null && !referee.getTournaments().isEmpty();
    }

    @Transactional
    public void delete(Referee referee) {
        if (referee != null && referee.getId() != null) {
            refereeDAO.deleteById(referee.getId());
        }
    }

    private void loadAll(){
        this.allReferees = refereeDAO.getAll();
    }
}
