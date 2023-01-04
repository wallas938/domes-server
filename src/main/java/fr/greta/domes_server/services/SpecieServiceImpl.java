package fr.greta.domes_server.services;

import fr.greta.domes_server.entities.Specie;
import fr.greta.domes_server.repositories.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class SpecieServiceImpl implements SpecieService{

    private final SpecieRepository specieRepository;

    public SpecieServiceImpl(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }
    @Override
    public void save(Specie specie) {

    }

    @Override
    public Collection<Specie> getAll() {
        return specieRepository.findAll();
    }

    @Override
    public Collection<Specie> getSpeciesByCategory(String categoryId) {
        return specieRepository.getSpeciesByCategoryId(UUID.fromString(categoryId));
    }

    @Override
    public Specie getSpeciesByName(String specieName) {
        return specieRepository.getSpeciesByName(specieName);
    }

    @Override
    public Collection<Specie> getSpeciesByCategoryName(String categoryName) {
        return specieRepository.findByCategoryName(categoryName);
    }
}
