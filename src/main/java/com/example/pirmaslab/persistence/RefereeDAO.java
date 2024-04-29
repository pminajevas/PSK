package com.example.pirmaslab.persistence;

import com.example.pirmaslab.entities.Referee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RefereeDAO {

    @Inject
    private EntityManager em;

    public List<Referee> getAll() {
        return this.em.createNamedQuery("Referee.findAll", Referee.class).getResultList();
    }

    public void persist(Referee referee) {
        this.em.persist(referee);
    }

    public void deleteById(Long id) {
        Referee entityToDelete = findOne(id);

        if (entityToDelete != null) {
            this.em.remove(entityToDelete);
        }
    }

    public Referee findOne(Long id) {
        return this.em.find(Referee.class, id);
    }
}
