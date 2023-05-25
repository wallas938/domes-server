package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.client.ClientEditDTO;
import fr.greta.domes_server.dtos.client.ClientGetDTO;
import fr.greta.domes_server.dtos.client.ClientPage;
import fr.greta.domes_server.dtos.client.ClientPostDTO;
import fr.greta.domes_server.entities.DomesResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClientService {
    ClientPage getClients(String lastname, String firstname, String phoneNumber, String email, int pageNumber, int pageSize);
    ClientPage searchBarGetClients(String lastname, String firstname, String phoneNumber, String email, int pageNumber, int pageSize);
    DomesResponse editClient(ClientEditDTO dto);

    Optional<ClientGetDTO> saveClient(ClientPostDTO clientPostDTO);

    Optional<ClientGetDTO> getClientByEmail(String clientId);
}
