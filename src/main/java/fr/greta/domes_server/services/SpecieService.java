package fr.greta.domes_server.services;


import fr.greta.domes_server.entities.Specie;

import java.util.Collection;

public interface SpecieService {
    void save(Specie specie);
    Collection<Specie> getAll();
    Collection<Specie> getSpeciesByCategory(String categoryId);
    Specie getSpeciesByName(String specieName);
    Collection<Specie> getSpeciesByCategoryName(String categoryName);
}
