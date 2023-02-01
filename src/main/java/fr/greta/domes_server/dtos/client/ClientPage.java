package fr.greta.domes_server.dtos.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class ClientPage {

    private Integer totalPages;

    private Integer totalElements;

    private List<ClientGetDTO> clients;
}
