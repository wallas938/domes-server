package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
}
