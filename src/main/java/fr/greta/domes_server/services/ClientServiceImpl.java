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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            return new DomesResponse("Client modifié avec succès", true);
        } catch (Exception e) {
            return new DomesResponse("Un probleme est survenu lors de la modification du client", true);
        }
    }

    @Override
    public Optional<ClientGetDTO> saveClient(ClientPostDTO clientPostDTO) {
        Client client = new Client();
        client.setRole(Role.ROLE_CLIENT);
        client.setAddress(clientPostDTO.getAddress());
        client.setEmail(clientPostDTO.getEmail());
        client.setLastname(clientPostDTO.getLastname());
        client.setFirstname(clientPostDTO.getFirstname());
        client.setPhoneNumber(clientPostDTO.getPhoneNumber());
        client.setPassword(passwordEncoder.encode(clientPostDTO.getPassword()));
        Optional<Client> savedClient = Optional.of(clientRepository.save(client));
        return savedClient.map(c -> {
            ClientGetDTO clientGetDTO = new ClientGetDTO();
            clientGetDTO.setId(c.getId());
            clientGetDTO.setLastname(c.getLastname());
            clientGetDTO.setFirstname(c.getFirstname());
            clientGetDTO.setAddress(c.getAddress());
            clientGetDTO.setEmail(c.getEmail());
            clientGetDTO.setPhoneNumber(c.getPhoneNumber());

            return Optional.of(clientGetDTO);
        }).orElse(Optional.empty());
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
        orderGetDTO.setArticles(order.getArticles());
        orderGetDTO.setTotal(order.getTotal());
        orderGetDTO.setId(order.getId());
        orderGetDTO.setPurchaseDate(order.getPurchaseDate());
        orderGetDTO.setShippingAddress(order.getShippingAddress());
        orderGetDTO.setNumberOfArticles(order.getNumberOfArticles());
        return orderGetDTO;
    }


}
