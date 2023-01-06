package fr.greta.domes_server.controllers;

import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.Category;
import fr.greta.domes_server.services.AnimalService;
import fr.greta.domes_server.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value = "/search")
    public ResponseEntity<AnimalPage> getAnimals(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "15") int pageSize,
            @RequestParam(defaultValue = "0") double minPrice,
            @RequestParam(defaultValue = "9999") double maxPrice,
            @RequestParam(defaultValue = "0") int minAge,
            @RequestParam(defaultValue = "100") int maxAge,
            @RequestParam(defaultValue = "%") String categoryName,
            @RequestParam(defaultValue = "%") String specieName) {
//        System.out.println(String.format("/search: minPrice:%s - maxPrice=%s - minAge=%s - maxAge=%s - categoryName=%s - specieName=%s - pageNumber=%s - pageSize=%s" , minPrice, maxPrice, minAge, maxAge, categoryName, specieName, pageNumber, pageSize));
        AnimalPage animalPage = animalService.getAnimals(minPrice, maxPrice, minAge, maxAge, categoryName, specieName, pageNumber, pageSize);

        if (animalPage != null) {
            return new ResponseEntity<>(animalPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<AnimalPage> getAnimalsBySize(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "15") int pageSize) {
        AnimalPage animalPage = animalService.getAnimalsBySize(pageNumber, pageSize);
        if (animalPage != null) {
            return new ResponseEntity<>(animalPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
