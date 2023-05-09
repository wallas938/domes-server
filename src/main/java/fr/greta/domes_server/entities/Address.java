package fr.greta.domes_server.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {
    @Column(nullable = false, length = 50)
    @NotNull(message = "country is missing")
    private String country;
    @Column(nullable = false, length = 50)
    @NotNull(message = "city is missing")
    private String city;
    @Column(nullable = false)
    @NotNull(message = "street is missing")
    private String street;
    @Column(nullable = false, length = 20)
    @NotNull(message = "zipcode is missing")
    private String zipCode;

    public String getCountry() {
        return country.toLowerCase();
    }

    public String getCity() {
        return city.toLowerCase();
    }
}
