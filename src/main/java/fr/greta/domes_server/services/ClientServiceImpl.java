package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.client.ClientGetDTO;
import fr.greta.domes_server.dtos.client.ClientPage;
import fr.greta.domes_server.entities.Client;
import fr.greta.domes_server.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientPage getClients(String lastname, String firstname, String phoneNumber, String email, int pageNumber, int pageSize) {
        Page<Client> page = clientRepository.getClients(
                lastname,
                firstname,
                phoneNumber,
                email,
                PageRequest.of(pageNumber, pageSize));

        ClientPage clientPage = new ClientPage();
        clientPage.setTotalPages(page.getTotalPages());
        clientPage.setTotalElements((int) page.getTotalElements());
        clientPage.setClients(castClientToClientDTO(page.getContent()));

        return clientPage;
    }

    private List<ClientGetDTO> castClientToClientDTO(List<Client> clients) {

        return clients.stream().map(client -> {
            ClientGetDTO clientGetDTO = new ClientGetDTO();
            clientGetDTO.setId(client.getId());
            clientGetDTO.setLastname(client.getLastname());
            clientGetDTO.setFirstname(client.getFirstname());
            clientGetDTO.setAddress(client.getAddress());
            clientGetDTO.setPhoneNumber(client.getPhoneNumber());
            clientGetDTO.setRegistrationDate(client.getRegistrationDate());
            clientGetDTO.setEmail(client.getEmail());
            clientGetDTO.setOrders(client.getOrders());

            return clientGetDTO;
        }).toList();
    }
}
