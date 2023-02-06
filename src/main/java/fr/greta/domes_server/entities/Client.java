package fr.greta.domes_server.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "t_client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Transient
    private String idStringified;
    @Column(length = 100, nullable = false)
    private String lastname;
    @Column(length = 100, nullable = false)
    private String firstname;
    @Column(length = 10, nullable = false)
    private String phoneNumber;
    @Column(length = 100, nullable = false)
    private String email;
    @Embedded
    private Address address;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate = LocalDate.now();

    public Client(String lastname, String firstname, String phoneNumber, String email, Address address, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
