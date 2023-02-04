package fr.greta.domes_server.services;

import fr.greta.domes_server.dtos.client.ClientPage;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    ClientPage getClients(String lastname, String firstname, String phoneNumber, String email, int pageNumber, int pageSize);

}