package com.example.pirmaslab.persistence;

import com.example.pirmaslab.entities.Tournament;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class TournamentDAO {

    @Inject
    private EntityManager em;

    public List<Tournament> getAll() {
        return this.em.createNamedQuery("Tournament.findAll", Tournament.class).getResultList();
    }

    public void persist(Tournament tournament) {
        if (tournament != null && !tournament.getName().isEmpty()) {
            this.em.persist(tournament);
        }
    }

    public void deleteById(Long id) {
        Tournament entityToDelete = findOne(id);

        if (entityToDelete != null)
        {
            this.em.remove(entityToDelete);
        }
    }

    public Tournament findOne(Long id) {
        return this.em.find(Tournament.class, id);
    }
}
