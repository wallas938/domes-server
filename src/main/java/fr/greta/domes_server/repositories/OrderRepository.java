package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findFirstByClientIdOrderByPurchaseDateAsc(UUID id);
    Order findFirstByClientId(UUID id);
}
