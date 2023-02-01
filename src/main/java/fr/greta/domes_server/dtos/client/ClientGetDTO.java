package fr.greta.domes_server.dtos.client;

import fr.greta.domes_server.entities.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ClientGetDTO {
    private UUID id;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private String address;
    private String email;
    private Collection<Order> orders;
    private LocalDate registrationDate = LocalDate.now();
}
