package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "t_animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String description;
    @Column
    private String mainPicture;
    @Column
    private String secondPicture;
    @Column
    private String thirdPicture;
    @Column
    private String fourthPicture;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Specie specie;
    @Column(nullable = false)
    @DecimalMin(value = "50.00", message = "Min price = 50.00")
    private double price;
    @Column(nullable = false)
    @Min(value = 1, message = "Min age = 1")
    @Max(value = 24, message = "Max age = 24")
    private int age;
    @Column
    private boolean sold;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate = LocalDate.now();

    public Animal(String description, Category category, Specie specie, double price, int age) {
        this.description = description;
        this.category = category;
        this.specie = specie;
        this.price = price;
        this.age = age;
    }
}
