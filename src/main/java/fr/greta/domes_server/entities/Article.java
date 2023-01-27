package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "t_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private double price;
    @ManyToOne
    private Specie specie;
    @ManyToOne
    private Category category;
}
