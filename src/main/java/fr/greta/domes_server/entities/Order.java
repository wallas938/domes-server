package fr.greta.domes_server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_orders_articles")
    private Collection<Article> articles;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("password")
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

    public Order(Collection<Article> articles, Client client, Address shippingAddress, int numberOfArticles, double total, LocalDate purchaseDate) {
        this.articles = articles;
        this.client = client;
        this.shippingAddress = shippingAddress;
        this.numberOfArticles = numberOfArticles;
        this.total = total;
        this.purchaseDate = purchaseDate;
    }
}
