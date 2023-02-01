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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CategoryRepository categoryRepository;
    private final SpecieRepository specieRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, CategoryRepository categoryRepository, SpecieRepository specieRepository) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
        this.specieRepository = specieRepository;
    }


    @Override
    public DomesResponse addAnimal(AnimalCreateDto animalCreateDto) {
        try {

            Category category = categoryRepository.getCategoryByName(animalCreateDto.getCategory());

            Specie specie = specieRepository.getSpeciesByName(animalCreateDto.getSpecie());

            if (category == null)
                return new DomesResponse("Catégorie null", false);

            if (specie == null)
                return new DomesResponse("Espece null", false);

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
            animal.setSold(false);

            animalRepository.save(animal);

            return new DomesResponse("Animal enregistré", true);
        } catch (Exception e) {
            return new DomesResponse("Erreur Serveur" + e.getMessage(), true);
        }
    }

    @Override
    public AnimalPage getAnimalsBySize(int pageNumber, int pageSize) {
        AnimalPage animalPage = new AnimalPage();
        Page<Animal> content = animalRepository.findAll(PageRequest.of(pageNumber, pageSize));
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
    public Animal editAnimal(AnimalEditDTO dto) {

        try {

            Category category = categoryRepository.getCategoryByName(dto.getCategory());

            Specie specie = specieRepository.getSpeciesByName(dto.getSpecie());

            if (category == null)
                return null;

            if (specie == null)
                return null;

            Optional<Animal> animal = animalRepository.findById(dto.getId());

            animal.get().setAge(dto.getAge());
            animal.get().setPrice(dto.getPrice());
            animal.get().setCategory(category);
            animal.get().setSpecie(specie);
            animal.get().setDescription(dto.getDescription());
            animal.get().setMainPicture(dto.getMainPicture());
            animal.get().setMainPicture(dto.getMainPicture());
            animal.get().setSecondPicture(dto.getSecondPicture());
            animal.get().setThirdPicture(dto.getThirdPicture());
            animal.get().setFourthPicture(dto.getFourthPicture());

            return animalRepository.save(animal.get());
        } catch (Exception e) {
            return null;
        }
    }
}
