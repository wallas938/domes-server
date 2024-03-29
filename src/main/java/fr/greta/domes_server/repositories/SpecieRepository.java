package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface SpecieRepository extends JpaRepository<Specie, UUID> {
    Collection<Specie> getSpeciesByCategoryId(UUID categoryId);
    Collection<Specie> findByCategoryName(String name);

    Specie getSpeciesByName(String specieName);
}
