package fr.greta.domes_server.controllers;

import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.dtos.client.ClientPage;
import fr.greta.domes_server.services.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/init")
    public ResponseEntity<ClientPage> getClients(
            HttpServletRequest request,
            @RequestParam(defaultValue = "%") String lastname,
            @RequestParam(defaultValue = "%") String firstname,
            @RequestParam(defaultValue = "%") String phoneNumber,
            @RequestParam(defaultValue = "%") String email,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "15") int pageSize) {
        StringBuffer url = request.getRequestURL();

        ClientPage clientPage = clientService.getClients(lastname, firstname, phoneNumber, email, pageNumber, pageSize);
        if (clientPage != null) {
            return new ResponseEntity<>(clientPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ClientPage> searchBarGetClients(
            HttpServletRequest request,
            @RequestParam(defaultValue = "%") String lastname,
            @RequestParam(defaultValue = "%") String firstname,
            @RequestParam(defaultValue = "%") String phoneNumber,
            @RequestParam(defaultValue = "%") String email,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "15") int pageSize) {

        ClientPage clientPage = clientService.searchBarGetClients(lastname, firstname, phoneNumber, email, pageNumber, pageSize);
        if (clientPage != null) {
            return new ResponseEntity<>(clientPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
