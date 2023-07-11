package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.animal.AnimalCreateDto;
import fr.greta.domes_server.dtos.animal.AnimalEditDTO;
import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.entities.Animal;
import fr.greta.domes_server.entities.Category;
import fr.greta.domes_server.entities.DomesResponse;
import fr.greta.domes_server.entities.Specie;
import fr.greta.domes_server.repositories.AnimalRepository;
import fr.greta.domes_server.repositories.CategoryRepository;
import fr.greta.domes_server.repositories.SpecieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CategoryRepository categoryRepository;
    private final SpecieRepository specieRepository;

    @Override
    public DomesResponse addAnimal(AnimalCreateDto animalCreateDto) {
        try {
            Category category = categoryRepository.getCategoryByName(animalCreateDto.getCategory());
            Specie specie = specieRepository.getSpeciesByName(animalCreateDto.getSpecie());
            if (category == null)
                return new DomesResponse(HttpStatus.BAD_REQUEST, null, "Catégorie introuvable");
            if (specie == null)
                return new DomesResponse(HttpStatus.BAD_REQUEST, null, "Espèce introuvable");
            Animal animal = new Animal();
            animal.setAge(animalCreateDto.getAge());
            animal.setPrice(animalCreateDto.getPrice());
            animal.setCategory(category);
            animal.setSpecie(specie);
            animal.setMainPicture(animalCreateDto.getMainPicture());
            animal.setMainPicture(animalCreateDto.getMainPicture());
            animal.setSecondPicture(animalCreateDto.getSecondPicture());
            animal.setThirdPicture(animalCreateDto.getThirdPicture());
            animal.setFourthPicture(animalCreateDto.getFourthPicture());
            animal.setDescription(animalCreateDto.getDescription());
            animal.setSold(false);
            animalRepository.save(animal);
            return new DomesResponse(HttpStatus.CREATED, null, "Produit enregistré avec succès");
        } catch (Exception e) {
            return new DomesResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }

    @Override
    public AnimalPage getAnimalsBySize(int pageNumber, int pageSize, String specieName) {
        AnimalPage animalPage = new AnimalPage();
        Page<Animal> content = animalRepository.findAnimalBySpecieNameEquals(specieName, PageRequest.of(pageNumber, pageSize));
        animalPage.setAnimals(content.getContent());
        animalPage.setTotalPages(content.getTotalPages());
        animalPage.setTotalElements(content.getNumberOfElements());
        return animalPage;
    }

    @Override
    public AnimalPage getAnimals(double minPrice, double maxPrice, int minAge, int maxAge, String categoryName, String specieName, int pageNumber, int pageSize) {

        Page<Animal> page = animalRepository.findAnimalByPriceAndAgeGreaterThanAndCategoryNameEqualsAndSpecieNameEquals(
                minPrice,
                maxPrice,
                minAge,
                maxAge,
                categoryName,
                specieName,
                PageRequest.of(pageNumber, pageSize));

        AnimalPage animalPage = new AnimalPage();
        animalPage.setAnimals(page.getContent());
        animalPage.setTotalPages(page.getTotalPages());
        animalPage.setTotalElements((int) page.getTotalElements());
        return animalPage;
    }

    @Override
    public Animal editAnimal(AnimalEditDTO dto, String animalId) {

        try {

            Category category = categoryRepository.getCategoryByName(dto.getCategory());

            Specie specie = specieRepository.getSpeciesByName(dto.getSpecie());

            if (category == null)
                return null;

            if (specie == null)
                return null;

            Optional<Animal> optionalAnimal = animalRepository.findById(UUID.fromString(animalId));

            optionalAnimal.ifPresent(animal -> {
                animal.setAge(dto.getAge());
                animal.setPrice(dto.getPrice());
                animal.setCategory(category);
                animal.setSpecie(specie);
                animal.setDescription(dto.getDescription());
                animal.setMainPicture(dto.getMainPicture());
                animal.setMainPicture(dto.getMainPicture());
                animal.setSecondPicture(dto.getSecondPicture());
                animal.setThirdPicture(dto.getThirdPicture());
                animal.setFourthPicture(dto.getFourthPicture());
            });

            return animalRepository.save(optionalAnimal.get());
        } catch (Exception e) {
            return null;
        }
    }
}
