package com.fastcode.dvdrentalsample2.domain.core.filmactor;

import com.fastcode.dvdrentalsample2.domain.core.abstractentity.AbstractEntity;
import com.fastcode.dvdrentalsample2.domain.core.actor.ActorEntity;
import com.fastcode.dvdrentalsample2.domain.core.film.FilmEntity;
import java.time.*;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FILM_ACTOR")
@IdClass(FilmActorId.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FilmActorEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ACTOR_ID", nullable = false)
    private Short actorId;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "FILM_ID", nullable = false)
    private Short filmId;

    @Basic
    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID", insertable = false, updatable = false)
    private ActorEntity actor;

    @ManyToOne
    @JoinColumn(name = "FILM_ID", insertable = false, updatable = false)
    private FilmEntity film;
}
