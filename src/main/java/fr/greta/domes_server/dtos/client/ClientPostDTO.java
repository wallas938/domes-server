package fr.greta.domes_server.dtos.client;

import fr.greta.domes_server.entities.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPostDTO {
    @NotNull(message = "lastname is missing")
    private String lastname;
    @NotNull(message = "firstname is missing")
    private String firstname;
    @NotNull(message = "phoneNumber is missing")
    private String phoneNumber;
    @NotNull(message = "email is missing")
    @Pattern(
            regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
            message = "Wrong format of email was used")
    @Column(unique = true)
    private String email;
    @NotNull(message = "password is missing")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Unauthorized password characters was used")
    private String password;
    @NotNull(message = "address is missing")
    private Address address;
}
