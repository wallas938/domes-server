package fr.greta.domes_server.dtos.order;

import fr.greta.domes_server.entities.Address;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.Client;
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
public class OrderPostDTO {
    private Collection<Animal> cart;
    private Address shippingAddress;
    private int numberOfArticles;
    private double total;
    private Client client;
}
