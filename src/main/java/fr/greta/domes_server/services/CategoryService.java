package fr.greta.domes_server.services;

import fr.greta.domes_server.entities.Category;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAll();
    void save(Category category);
}
