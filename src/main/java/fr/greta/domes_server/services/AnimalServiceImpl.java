package fr.greta.domes_server.services;

import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.repositories.AnimalRepository;
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
}
