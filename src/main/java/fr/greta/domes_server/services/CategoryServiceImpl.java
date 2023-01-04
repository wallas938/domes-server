package fr.greta.domes_server.services;

import fr.greta.domes_server.entities.Category;
import fr.greta.domes_server.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Collection<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {

    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }
}
