package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

}
