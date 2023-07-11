package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.order.OrderPostDTO;
import fr.greta.domes_server.entities.DomesResponse;

public interface OrderService {
    DomesResponse saveOrder(OrderPostDTO orderPostDTO);
}
