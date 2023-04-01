package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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

    public void setLastname(String lastname) {
        this.lastname = lastname.toLowerCase();
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname.toLowerCase();
    }
}
