package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "t_employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 55)
    private String lastname;
    @Column(length = 55)
    private String firstname;
    @Column(length = 100)
    private String email;
    @Column(length = 255)
    private String address;
//    @Column
//    @Temporal(TemporalType.DATE)
//    private Date birthdate;
}
