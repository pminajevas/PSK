package com.example.pirmaslab.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tournament.findAll", query = "select t from Tournament as t")
})
@Getter @Setter
@EqualsAndHashCode
public class Tournament {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Basic(optional = false)
    private String Name;

    @Basic(optional = false)
    private String Game;

    @Basic(optional = false)
    @Column(name = "EVENT_START_DATE")
    private LocalDate EventStartDate;

    @Basic(optional = false)
    @Column(name = "REGISTRATION_START_DATE")
    private LocalDate RegStartDate;

    @Basic(optional = false)
    @Column(name = "REGISTRATION_END_DATE")
    private LocalDate RegEndDate;

    @Basic(optional = false)
    private Integer MaxPlayers;

    @ManyToMany(mappedBy = "Tournaments")
    private List<Player> Players;

    @ManyToOne(optional = false)
    @JoinColumn(name = "REFEREE_ID")
    private Referee Referee;

    @Basic
    private Integer TournamentEngagementCount;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
