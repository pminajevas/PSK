package com.example.pirmaslab.persistence;

import com.example.pirmaslab.entities.Referee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class RefereeDAO implements GenericDAO<Referee> {

    @Inject
    private EntityManager em;

    public List<Referee> getAll() {
        return this.em.createNamedQuery("Referee.findAll", Referee.class).getResultList();
    }

    @Transactional
    public void persist(Referee referee) {
        this.em.persist(referee);
    }

    @Transactional
    public void delete(Long id) {
        Referee entityToDelete = findOne(id);

        if (entityToDelete != null) {
            this.em.remove(entityToDelete);
        }
    }

    public Referee findOne(Long id) {
        return this.em.find(Referee.class, id);
    }

    @Transactional
    public void update(Referee referee) {
        this.em.merge(referee);
    }
}
