package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.DomesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface DomesUserRepository extends JpaRepository<DomesUser, UUID> {
    Optional<DomesUser> findByEmail(String email);
}
