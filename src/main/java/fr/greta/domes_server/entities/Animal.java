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
    @DecimalMin(value = "50.00", message = "Min age = 1.0")
    private double price;
    @Column(nullable = false)
    @Min(value = 1, message = "Min age = 1")
    @Max(value = 10, message = "Max age = 10")
    private int age;
    @Column
    private boolean sold;
    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate = LocalDate.now();
}
