package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String reference;
    @OneToMany
    private Collection<Animal> animals;
    @ManyToOne
    private Client client;
    @Column
    private String shippingAddress;
    @Column
    private double total;
}
