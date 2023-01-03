package fr.greta.domes_server.controllers;

import fr.greta.domes_server.entities.Specie;
import fr.greta.domes_server.services.SpecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/species")
public class SpecieController {

    private final SpecieService specieService;

    public SpecieController(SpecieService specieService) {
        this.specieService = specieService;
    }

    @GetMapping
    public ResponseEntity<List<Specie>> getSpecies() {
        List<Specie> species = (List<Specie>) specieService.getAll();
        System.out.println(species);
        if(species != null) {
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(params = "categoryId")
    public ResponseEntity<List<Specie>> getSpeciesByCategory(@RequestParam("categoryId") String categoryId) {
        List<Specie> species = (List<Specie>) specieService.getSpeciesByCategory(categoryId);
        if(species != null) {
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(params = "categoryName")
    public ResponseEntity<List<Specie>> getSpeciesByCategoryName(@RequestParam("categoryName") String categoryName) {
        List<Specie> species = (List<Specie>) specieService.getSpeciesByCategoryName(categoryName);
        if(species != null) {
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
