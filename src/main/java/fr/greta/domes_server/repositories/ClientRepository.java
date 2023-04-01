package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByEmail(String email);
    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE " +
            "c.lastname LIKE %:lastname% " +
            "AND c.firstname LIKE %:firstname% " +
            "AND c.phoneNumber LIKE %:phoneNumber% " +
            "AND c.email LIKE %:email%")
    Page<Client> getClients(
            @Param("lastname") String lastname,
            @Param("firstname") String firstname,
            @Param("phoneNumber") String phoneNumber,
            @Param("email") String email,
            Pageable pageable);

    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE " +
            "c.lastname LIKE %:lastname% " +
            "OR c.firstname LIKE %:firstname% " +
            "OR c.phoneNumber LIKE %:phoneNumber% " +
            "OR c.email LIKE %:email%")
    Page<Client> findBySearchTerm(
            @Param("lastname") String lastname,
            @Param("firstname") String firstname,
            @Param("phoneNumber") String phoneNumber,
            @Param("email") String email,
            Pageable pageable);

    Client findByFirstname(String firstname);
}
