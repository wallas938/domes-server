package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface AnimalService {
    void addAnimal(Animal animal);
    Animal getAnimal(String uuid);
    Collection<Animal> getAnimal();

    AnimalPage getAnimalsBySize(int pageSize, int size);
    AnimalPage getAnimals(double minPrice, double maxPrice, int minAge, int maxAge, String categoryName, String specieName, int pageNumber, int pageSize);
}
