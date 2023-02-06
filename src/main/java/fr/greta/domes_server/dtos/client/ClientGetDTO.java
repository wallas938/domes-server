package fr.greta.domes_server.dtos.client;

import fr.greta.domes_server.dtos.order.OrderGetDTO;
import fr.greta.domes_server.entities.Address;
import fr.greta.domes_server.entities.Order;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientGetDTO {
    private UUID id;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private Address address;
    private String email;
    private LocalDate registrationDate = LocalDate.now();
    private OrderGetDTO lastOrder;
}
