package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    @Query("SELECT a " +
            "FROM Animal a " +
            "WHERE a.price >= :minPrice AND a.price <= :maxPrice " +
            "AND a.age >= :minAge AND a.age <= :maxAge " +
            "AND a.category.name LIKE :categoryName " +
            "AND a.specie.name LIKE :specieName")
    Page<Animal> findAnimalByPriceAndAgeGreaterThanAndCategoryNameEqualsAndSpecieNameEquals(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("minAge") int minAge,
            @Param("maxAge") int maxAge,
            @Param("categoryName") String categoryName,
            @Param("specieName") String specieName,
            Pageable pageable);

    @Query("SELECT a " +
            "FROM Animal a " +
            "WHERE a.specie.name LIKE :specieName")
    Page<Animal> findAnimalBySpecieNameEquals(
            @Param("specieName") String specieName,
            Pageable pageable);
}
