package fr.greta.domes_server.services;

import fr.greta.domes_server.configuration.Role;
import fr.greta.domes_server.dtos.client.ClientEditDTO;
import fr.greta.domes_server.dtos.client.ClientGetDTO;
import fr.greta.domes_server.dtos.client.ClientPage;
import fr.greta.domes_server.dtos.client.ClientPostDTO;
import fr.greta.domes_server.dtos.order.OrderGetDTO;
import fr.greta.domes_server.entities.*;
import fr.greta.domes_server.repositories.ClientRepository;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final DomesUserRepository domesUserRepository;
    private final PasswordEncoder passwordEncoder;

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
        clientPage.setClients(generateListOfClientGetDTO(page.getContent()));

        return clientPage;
    }

    @Override
    public ClientPage searchBarGetClients(String lastname, String firstname, String phoneNumber, String email, int pageNumber, int pageSize) {
        Page<Client> page = clientRepository.findBySearchTerm(
                lastname.toLowerCase(),
                firstname.toLowerCase(),
                phoneNumber.toLowerCase(),
                email.toLowerCase(),
                PageRequest.of(pageNumber, pageSize));

        ClientPage clientPage = new ClientPage();
        clientPage.setTotalPages(page.getTotalPages());
        clientPage.setTotalElements((int) page.getTotalElements());
        clientPage.setClients(generateListOfClientGetDTO(page.getContent()));

        return clientPage;
    }

    @Override
    public DomesResponse editClient(ClientEditDTO dto) {
        try {
            Optional<Client> optionalClient = clientRepository.findById(dto.getId());
            optionalClient.get().setLastname(dto.getLastname().toLowerCase());
            optionalClient.get().setFirstname(dto.getFirstname().toLowerCase());
            optionalClient.get().setPhoneNumber(dto.getPhoneNumber().toLowerCase());
            optionalClient.get().setEmail(dto.getEmail().toLowerCase());
            optionalClient.get().setAddress(dto.getAddress());
            clientRepository.save(optionalClient.get());
            return new DomesResponse(HttpStatus.ACCEPTED, null, "Client modifié avec succès");
        } catch (Exception e) {
            return new DomesResponse(HttpStatus.BAD_REQUEST, null, "Client introuvable");
        }
    }

    @Override
    public DomesResponse saveClient(ClientPostDTO clientPostDTO) {

        Optional<Client> isClientExist = clientRepository.findByEmail(clientPostDTO.getEmail());

        if (isClientExist.isPresent())
            return new DomesResponse(HttpStatus.CONFLICT, null, "Cette Email existe déja");

        Client client = new Client();
        client.setRole(Role.ROLE_CLIENT);
        client.setAddress(clientPostDTO.getAddress());
        client.setEmail(clientPostDTO.getEmail());
        client.setLastname(clientPostDTO.getLastname());
        client.setFirstname(clientPostDTO.getFirstname());
        client.setPhoneNumber(clientPostDTO.getPhoneNumber());
        client.setPassword(passwordEncoder.encode(clientPostDTO.getPassword()));

        Optional<Client> savedClient = Optional.of(clientRepository.save(client));

        ClientGetDTO clientGetDTO = new ClientGetDTO();
        clientGetDTO.setId(savedClient.get().getId());
        clientGetDTO.setLastname(savedClient.get().getLastname());
        clientGetDTO.setFirstname(savedClient.get().getFirstname());
        clientGetDTO.setAddress(savedClient.get().getAddress());
        clientGetDTO.setEmail(savedClient.get().getEmail());
        clientGetDTO.setPhoneNumber(savedClient.get().getPhoneNumber());

        return new DomesResponse(HttpStatus.CREATED, savedClient, "Client enregistré avec succès");
    }

    @Override
    public Optional<ClientGetDTO> getClientByEmail(String email) {
        return clientRepository.findByEmail(email).map(client -> {
            DomesUser domesUser = domesUserRepository.findByEmail(client.getEmail()).orElseThrow();
            return convertClientDBIntoClientGetDTO(client, domesUser);
        });
    }


    private ClientGetDTO convertClientDBIntoClientGetDTO(Client client, DomesUser domesUser) {
        ClientGetDTO clientGetDTO = new ClientGetDTO();
        clientGetDTO.setLastname(client.getLastname());
        clientGetDTO.setFirstname(client.getFirstname());
        clientGetDTO.setAddress(client.getAddress());
        clientGetDTO.setPhoneNumber(client.getPhoneNumber());
        clientGetDTO.setRegistrationDate(client.getRegistrationDate());
        clientGetDTO.setEmail(client.getEmail());
        clientGetDTO.setId(domesUser.getId());
        Order lastOrder = orderRepository.findFirstByClientId(domesUser.getId());
        if (lastOrder != null)
            clientGetDTO.setLastOrder(generateOrderGetDTO(lastOrder));
        return clientGetDTO;
    }

    private List<ClientGetDTO> generateListOfClientGetDTO(List<Client> clients) {
        return clients.stream().map(client -> {
            DomesUser domesUser = domesUserRepository.findByEmail(client.getEmail()).get();
            return convertClientDBIntoClientGetDTO(client, domesUser);
        }).toList();
    }

    private OrderGetDTO generateOrderGetDTO(Order order) {
        OrderGetDTO orderGetDTO = new OrderGetDTO();
        orderGetDTO.setCart(order.getCart());
        orderGetDTO.setTotal(order.getTotal());
        orderGetDTO.setId(order.getId());
        orderGetDTO.setPurchaseDate(order.getPurchaseDate());
        orderGetDTO.setShippingAddress(order.getShippingAddress());
        orderGetDTO.setNumberOfArticles(order.getNumberOfArticles());
        return orderGetDTO;
    }


}
