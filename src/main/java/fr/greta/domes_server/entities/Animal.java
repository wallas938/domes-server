package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "t_animal")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String description;
    @Column(nullable = true)
    private String mainPicture;
    @Column(nullable = true)
    private String secondPicture;
    @Column(nullable = true)
    private String thirdPicture;
    @Column(nullable = true)
    private String fourthPicture;
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Specie specie;
    @Column
    private double price;
    @Column
    private int age;

    public Animal(String description, Category category, Specie specie, double price, int age) {
        this.description = description;
        this.category = category;
        this.specie = specie;
        this.price = price;
        this.age = age;
    }
}
