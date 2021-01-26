package com.carona.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany( targetEntity=Passanger.class )
    private List passangers;

    @OneToOne
    private Driver driver;
}
