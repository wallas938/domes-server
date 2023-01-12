package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.animal.AnimalCreateDto;
import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.entities.DomesResponse;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {
    DomesResponse addAnimal(AnimalCreateDto animal);
    AnimalPage getAnimalsBySize(int pageSize, int size);
    AnimalPage getAnimals(double minPrice, double maxPrice, int minAge, int maxAge, String categoryName, String specieName, int pageNumber, int pageSize);
}
