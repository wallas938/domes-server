package fr.greta.domes_server.dtos.animal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AnimalEditDTO {

    @NotNull(message = "L'ID de l'animal est manquant")
    private UUID id;
    @NotNull(message = "Ajouter une catégory")
    private String category;
    @NotNull(message = "Ajouter une espèce")
    private String specie;
    @NotNull(message = "Prix invalide...")
    @DecimalMin(value = "50.00", message = "Le prix doit être superieur ou egale à 50.00 Euro")
    private double price;
    @NotNull(message = "Age invalide...")
    @Min(value = 1, message = "L'age doit être superieur ou égale a 1 ")
    private int age;
    @Nullable
    private String description;
    @Nullable
    private String mainPicture;
    @Nullable
    private String secondPicture;
    @Nullable
    private String thirdPicture;
    @Nullable
    private String fourthPicture;
}
