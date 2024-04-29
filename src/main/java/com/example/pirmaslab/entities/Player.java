package com.example.pirmaslab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "select p from Player as p")
})
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Basic(optional = false)
    private String Name;

    @Basic(optional = false)
    private String Surname;

    @Basic(optional = false)
    private String Username;

    @Basic(optional = false)
    private String Email;

    @ManyToMany
    private List<Tournament> Tournaments;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
