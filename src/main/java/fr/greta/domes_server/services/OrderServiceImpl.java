package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.order.OrderGetDTO;
import fr.greta.domes_server.dtos.order.OrderPostDTO;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.Client;
import fr.greta.domes_server.entities.DomesResponse;
import fr.greta.domes_server.entities.Order;
import fr.greta.domes_server.repositories.AnimalRepository;
import fr.greta.domes_server.repositories.ClientRepository;
import fr.greta.domes_server.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final AnimalRepository animalRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, AnimalRepository animalRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    @Transactional
    public DomesResponse saveOrder(OrderPostDTO orderPostDTO) {
        try {
            Client client = clientRepository.findById(orderPostDTO.getClient().getId()).orElseThrow();
            Order order = new Order();
            order.setNumberOfArticles(orderPostDTO.getNumberOfArticles());
            order.setTotal(orderPostDTO.getTotal());
            order.setClient(client);
            order.setNumberOfArticles(orderPostDTO.getNumberOfArticles());
            order.setShippingAddress(orderPostDTO.getShippingAddress());


            List<Animal> animals = animalRepository
                    .findAllById(orderPostDTO.getCart().stream().map(Animal::getId)
                            .collect(Collectors.toList()));

            order.setCart(animals);

            Order saved = orderRepository.save(order);

            animals.forEach(animal -> {
                animal.setOrder(saved);
            });

            OrderGetDTO orderGetDTO = new OrderGetDTO();


            orderGetDTO.setCart(saved.getCart());
            orderGetDTO.setPurchaseDate(saved.getPurchaseDate());
            orderGetDTO.setTotal(saved.getTotal());
            orderGetDTO.setShippingAddress(saved.getShippingAddress());
            orderGetDTO.setNumberOfArticles(saved.getNumberOfArticles());
            orderGetDTO.setId(saved.getId());

            client.getOrders().add(saved);

            clientRepository.save(client);

            return new DomesResponse(HttpStatus.CREATED, orderGetDTO, "Votre commande a été enregistrée");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new DomesResponse(HttpStatus.NOT_FOUND, null, "Client introuvable");
        }
    }

}
