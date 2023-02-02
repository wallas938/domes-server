package fr.greta.domes_server.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {
    private String country;
    private String city;
    private String street;
    private String zipcode;
}
