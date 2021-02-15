package com.carona.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "travels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long value;

    @Column(nullable = false)
    private boolean open;

    @Column(nullable = false)
    private int max_passangers;

    @JoinTable(
            name = "travels_passangers",
            joinColumns = @JoinColumn(name = "travel_id"),
            inverseJoinColumns = @JoinColumn(name = "passanger_id"))
    @ManyToMany
    Set<Passanger> passangers;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private Driver driver;
}
