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

    @Column(nullable = false, name = "max_passangers")
    private int maxPassangers;

    @JoinTable(
            name = "travels_passangers",
            joinColumns = @JoinColumn(name = "travel_id"),
            inverseJoinColumns = @JoinColumn(name = "passanger_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    List<Passanger> passangers;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private Driver driver;

    public Travel(Long value , boolean open , int maxPassangers , List<Passanger> passangers , Driver driver) {
        this.value = value;
        this.open = open;
        this.maxPassangers = maxPassangers;
        this.passangers = passangers;
        this.driver = driver;
    }
}
