package com.carona.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "passangers")
@NoArgsConstructor
@AllArgsConstructor
public class Passanger{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @Column(name="travel_id")
    private Travel travel;

    @Column
    private Long user_id;
}
