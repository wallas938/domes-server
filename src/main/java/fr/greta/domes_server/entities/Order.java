package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private UUID reference;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_orders_articles")
    private Collection<Article> articles;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @Embedded
    private Address shippingAddress;
    @Column
    private int numberOfArticles;
    @Column
    private double total;
    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate purchaseDate = LocalDate.now();
}
