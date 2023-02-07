package fr.greta.domes_server.dtos.client;

import fr.greta.domes_server.entities.Address;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientEditDTO {
    private UUID id;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private Address address;
    private String email;

    public String getLastname() {
        return lastname.toLowerCase();
    }

    public String getFirstname() {
        return firstname.toLowerCase();
    }
}


