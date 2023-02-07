package fr.greta.domes_server.entities;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {
    @Column(nullable = false, length = 50)
    private String country;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false, length = 20)
    private String zipcode;

    public String getCountry() {
        return country.toLowerCase();
    }

    public String getCity() {
        return city.toLowerCase();
    }
}
