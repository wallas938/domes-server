package fr.greta.domes_server.controllers;

import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.Category;
import fr.greta.domes_server.services.AnimalService;
import fr.greta.domes_server.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAnimals() {
        List<Animal> animals = (List<Animal>) animalService.getAll();
        System.out.println(animals);
        if(animals != null) {
            return new ResponseEntity<>(animals, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
