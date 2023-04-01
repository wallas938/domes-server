package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.client.ClientEditDTO;
import fr.greta.domes_server.dtos.client.ClientGetDTO;
import fr.greta.domes_server.dtos.client.ClientPage;
import fr.greta.domes_server.dtos.order.OrderGetDTO;
import fr.greta.domes_server.entities.*;
import fr.greta.domes_server.repositories.ClientRepository;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final DomesUserRepository domesUserRepository;

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


    private List<ClientGetDTO> generateListOfClientGetDTO(List<Client> clients) {
        return clients.stream().map(client -> {
            DomesUser domesUser = domesUserRepository.findByEmail(client.getEmail()).get();
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
