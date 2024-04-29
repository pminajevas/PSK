package com.example.pirmaslab.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Referee.findAll", query = "select r from Referee as r")
})
@Getter @Setter
@EqualsAndHashCode
public class Referee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Basic(optional = false)
    private String Name;

    @Basic(optional = false)
    private String Surname;

    @Basic(optional = false)
    private String Email;

    @OneToMany(mappedBy = "Referee")
    private List<Tournament> Tournaments;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
