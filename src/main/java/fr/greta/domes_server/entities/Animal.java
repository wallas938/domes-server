package fr.greta.domes_server.entities;

import fr.greta.domes_server.enums.Category;
import fr.greta.domes_server.enums.Specie;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.UUID;
@Entity
@Table(name = "t_animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String description;
    @Column
    private String imageUrl;
    @Column
    private Category category;
    @Column
    private Specie specie;
    @Column
    private double price;
    @Column
    private int quantity;
}
