package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
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
    @Column
    private String lastname;
    @Column
    private String firstname;
    @Column
    private String telephone;
    @Column
    private String address;
    @OneToMany(mappedBy = "client")
    private Collection<Order> orders;
}
