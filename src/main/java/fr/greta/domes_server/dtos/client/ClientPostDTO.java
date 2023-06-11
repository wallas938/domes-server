package fr.greta.domes_server.dtos.client;

import fr.greta.domes_server.entities.Address;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPostDTO {
    @NotEmpty(message = "Nom manquant")
    private String lastname;
    @NotEmpty(message = "Prénom manquant")
    private String firstname;
    @NotEmpty(message = "Numéro de téléphone manquant")
    private String phoneNumber;
    @NotEmpty(message = "Adresse mail manquante")
    @Pattern(
            regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
            message = "Veuillez revoir le format de votre adresse mail")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Mot de passe manquant")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Votre mot de passe doit contenir au moins 8 caractères dont une Majuscule et un chiffre")
    private String password;
    @Valid
    @NotNull(message = "Veuillez entrer une adresse valide")
    private Address address;
}
