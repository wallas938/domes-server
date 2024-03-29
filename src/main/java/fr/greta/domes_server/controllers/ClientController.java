package fr.greta.domes_server.controllers;

import fr.greta.domes_server.dtos.client.ClientEditDTO;
import fr.greta.domes_server.dtos.client.ClientGetDTO;
import fr.greta.domes_server.dtos.client.ClientPage;
import fr.greta.domes_server.entities.DomesResponse;
import fr.greta.domes_server.services.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public ResponseEntity<ClientGetDTO> getClient(@RequestParam("email") String email) {
        Optional<ClientGetDTO> clientGetDTO = clientService.getClientByEmail(email);

        return clientGetDTO.map(client -> new ResponseEntity<>(client, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
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

        ClientPage clientPage = clientService.getClients(lastname, firstname, phoneNumber, email, pageNumber, pageSize);
        if (clientPage != null) {
            return new ResponseEntity<>(clientPage, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/search")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
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

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<String> editClient(@PathVariable String clientId, @RequestBody ClientEditDTO clientGetDTO) {
        DomesResponse result = clientService.editClient(clientGetDTO);
        if (result.getCode() == HttpStatus.ACCEPTED) {
            return new ResponseEntity<>(result.getMessage(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(result.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
