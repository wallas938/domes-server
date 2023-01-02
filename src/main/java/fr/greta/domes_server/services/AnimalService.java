package fr.greta.domes_server.services;

import fr.greta.domes_server.entities.Animal;

import java.util.Collection;

public interface AnimalService {
    void addAnimal(Animal animal);
    Animal getAnimal(String uuid);
    Collection<Animal> getAnimals();
}
