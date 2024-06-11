package com.diaz.App_Restaurant.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "apprenants")
public class Apprenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String matricule;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Promotion promotion;
}
