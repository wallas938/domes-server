package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DomesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 100)
    private String lastname;
    @Column(length = 100)
    private String firstname;
    @Column(length = 100)
    private String email;
    @Column
    private String password;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate = LocalDate.now();

    public DomesUser(String lastname, String firstname, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }
}
