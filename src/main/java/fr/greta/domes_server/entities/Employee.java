package fr.greta.domes_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "t_employee")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Employee extends DomesUser {
    public Employee(String lastname, String firstname, String email, String password) {
        super(lastname, firstname, email, password);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
