package fr.greta.domes_server.services;


import fr.greta.domes_server.entities.Specie;

import java.util.Collection;

public interface SpecieService {
    Collection<Specie> getAll();
    Collection<Specie> getSpeciesByCategory(String categoryId);
    Collection<Specie> getSpeciesByCategoryName(String categoryName);
}
