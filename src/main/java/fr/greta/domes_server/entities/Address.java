package fr.greta.domes_server.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {
    @Column(nullable = false, length = 50)
    @NotEmpty(message = "country is missing")
    private String country;
    @Column(nullable = false, length = 50)
    @NotEmpty(message = "city is missing")
    private String city;
    @Column(nullable = false)
    @NotEmpty(message = "street is missing")
    private String street;
    @Column(nullable = false, length = 20)
    @NotEmpty(message = "zipcode is missing")
    private String zipCode;

    public String getCountry() {
        return country.toLowerCase();
    }

    public String getCity() {
        return city.toLowerCase();
    }
}
