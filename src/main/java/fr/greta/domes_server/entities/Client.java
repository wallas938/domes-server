package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "t_client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100)
    private String lastname;
    @Column(length = 100)
    private String firstname;
    @Column(length = 10)
    private String telephone;
    @Column(length = 100)
    private String address;
}
