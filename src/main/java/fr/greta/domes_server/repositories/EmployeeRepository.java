package fr.greta.domes_server.repositories;

import fr.greta.domes_server.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByFirstname(String name);
    Optional<Employee> findByEmail(String name);
    Page<Employee> findByFirstnameContaining(String name, Pageable pageable);
}
