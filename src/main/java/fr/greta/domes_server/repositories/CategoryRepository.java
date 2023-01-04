package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category getCategoryByName(String categoryName);
}
