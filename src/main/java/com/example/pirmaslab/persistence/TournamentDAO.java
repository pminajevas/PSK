package com.example.pirmaslab.persistence;

import com.example.pirmaslab.entities.Tournament;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class TournamentDAO implements GenericDAO<Tournament> {

    @Inject
    private EntityManager em;

    public List<Tournament> getAll() {
        return this.em.createNamedQuery("Tournament.findAll", Tournament.class).getResultList();
    }

    @Transactional
    public void persist(Tournament tournament) {
        if (tournament != null && !tournament.getName().isEmpty()) {
            this.em.persist(tournament);
        }
    }

    @Transactional
    public void delete(Long id) {
        Tournament entityToDelete = findOne(id);

        if (entityToDelete != null)
        {
            this.em.refresh(entityToDelete);
            this.em.remove(entityToDelete);
        }
    }

    public Tournament findOne(Long id) {
        return this.em.find(Tournament.class, id);
    }

    @Transactional
    public void update(Tournament tournament) {
        this.em.merge(tournament);
    }
}
