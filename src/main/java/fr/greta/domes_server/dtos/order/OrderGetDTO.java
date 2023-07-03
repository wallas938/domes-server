package fr.greta.domes_server.dtos.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.greta.domes_server.entities.Address;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderGetDTO {
    private UUID id;
    private Collection<Animal> animals;
    private Address shippingAddress;
    private int numberOfArticles;
    private double total;
    private LocalDate purchaseDate = LocalDate.now();
}
