package com.example.pirmaslab.persistence;

import com.example.pirmaslab.entities.Player;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class PlayerDAO implements GenericDAO<Player> {

    @Inject
    private EntityManager em;

    public List<Player> getAll() {
        return this.em.createNamedQuery("Player.findAll", Player.class).getResultList();
    }

    public void persist(Player player) {
        this.em.persist(player);
    }

    public void delete(Long id) {
        Player entityToDelete = findOne(id);

        if (entityToDelete != null) {
            this.em.refresh(entityToDelete);
            this.em.remove(entityToDelete);
        }
    }

    public void update(Player player) {
        this.em.merge(player);
    }

    public Player findOne(Long id) {
        return this.em.find(Player.class, id);
    }
}
