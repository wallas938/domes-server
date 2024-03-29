package fr.greta.domes_server.controllers;

import fr.greta.domes_server.dtos.animal.AnimalEditDTO;
import fr.greta.domes_server.dtos.animal.AnimalCreateDto;
import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.DomesResponse;
import fr.greta.domes_server.services.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping(value = "/search")
    public ResponseEntity<AnimalPage> getAnimals(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "15") int pageSize,
            @RequestParam(defaultValue = "50") double minPrice,
            @RequestParam(defaultValue = "999") double maxPrice,
            @RequestParam(defaultValue = "1") int minAge,
            @RequestParam(defaultValue = "24") int maxAge,
            @RequestParam(defaultValue = "%") String categoryName,
            @RequestParam(defaultValue = "%") String specieName) {
//        System.out.println(String.format("/search: minPrice:%s - maxPrice=%s - minAge=%s - maxAge=%s - categoryName=%s - specieName=%s - pageNumber=%s - pageSize=%s" , minPrice, maxPrice, minAge, maxAge, categoryName, specieName, pageNumber, pageSize));
        AnimalPage animalPage = animalService.getAnimals(minPrice, maxPrice, minAge, maxAge, categoryName, specieName, pageNumber, pageSize);
//        System.out.println(categoryName);
        if (animalPage != null) {
            return new ResponseEntity<>(animalPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<AnimalPage> getAnimalsBySize(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "15") int pageSize, @RequestParam(defaultValue = "%") String specieName) {
//        System.out.printf("specieName=%s - pageNumber=%s - pageSize=%s%n",specieName, pageNumber, pageSize);
        AnimalPage animalPage = animalService.getAnimalsBySize(pageNumber, pageSize, specieName);
        if (animalPage != null) {
            return new ResponseEntity<>(animalPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<String> postAnimal(@RequestBody @Valid AnimalCreateDto dto) {
        DomesResponse response = animalService.addAnimal(dto);
        if (response.getCode() == HttpStatus.CREATED)
            return new ResponseEntity<>("Animal Ajouté avec succes", response.getCode());

        return new ResponseEntity<>("Erreur", response.getCode());
    }

    @PutMapping(value = "{animalId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<Animal> editAnimal(@RequestBody @Valid AnimalEditDTO dto, @PathVariable String animalId) {

        Animal animal = animalService.editAnimal(dto, animalId);

        if (animal != null)
            return new ResponseEntity<>(animal, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
