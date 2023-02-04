package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "t_specie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100, nullable = false, unique = true)
    @NotBlank
    private String name;
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
