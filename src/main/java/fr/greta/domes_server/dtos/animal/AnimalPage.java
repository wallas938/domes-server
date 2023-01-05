package fr.greta.domes_server.dtos.animal;

import fr.greta.domes_server.entities.Animal;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class AnimalPage {
    private Integer totalPages;

    private Integer totalElements;

    private List<Animal> animals;
}
