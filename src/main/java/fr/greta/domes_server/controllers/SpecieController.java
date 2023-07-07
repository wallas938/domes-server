package fr.greta.domes_server.controllers;

import fr.greta.domes_server.entities.Specie;
import fr.greta.domes_server.services.SpecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/species")
@RequiredArgsConstructor
public class SpecieController {
    private final SpecieService specieService;

    @GetMapping
    public ResponseEntity<List<Specie>> getSpecies() {
        List<Specie> species = (List<Specie>) specieService.getAll();
        if(species != null) {
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "{categoryId}")
    public ResponseEntity<List<Specie>> getSpeciesByCategoryId(@PathVariable("categoryId") String categoryId) {
        List<Specie> species = (List<Specie>) specieService.getSpeciesByCategory(categoryId);
        System.out.println(species);
        if(species != null) {
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "categoryName")
    public ResponseEntity<List<Specie>> getSpeciesByCategoryName(@RequestParam("categoryName") String categoryName) {
        List<Specie> species = (List<Specie>) specieService.getSpeciesByCategoryName(categoryName);
        if(species != null) {
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
