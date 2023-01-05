package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.repositories.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Override
    public void addAnimal(Animal animal) {
    }

    @Override
    public Animal getAnimal(String uuid) {
        return null;
    }

    @Override
    public Collection<Animal> getAll() {
        return animalRepository.findAll();
    }

    @Override
    public AnimalPage getAnimalsBySize(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        AnimalPage animalPage = new AnimalPage();
        Page<Animal> content = animalRepository.findAll(pageable);
        animalPage.setAnimals(content.getContent());
        animalPage.setTotalPages(content.getTotalPages());
        animalPage.setTotalElements(content.getNumberOfElements());
        return animalPage;
    }
}
