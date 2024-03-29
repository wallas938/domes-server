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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Client extends DomesUser {
    @Column(length = 10, nullable = false)
    private String phoneNumber;
    @Embedded
    private Address address;
    @OneToMany(cascade=CascadeType.MERGE, mappedBy="client", fetch = FetchType.EAGER)
    private Collection<Order> orders;

    public Client(String lastname, String firstname, String email, String password) {
        super(lastname, firstname, email, password);
    }

    @Override
    public String toString() {
        return super.toString() + " Client(phoneNumber="+phoneNumber+" address="+address+ "orders="+orders+")";
    }
}
