package fr.greta.domes_server.controllers;

import fr.greta.domes_server.dtos.client.ClientPostDTO;
import fr.greta.domes_server.dtos.order.OrderPostDTO;
import fr.greta.domes_server.entities.DomesResponse;
import fr.greta.domes_server.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<DomesResponse> saveClient(@RequestBody @Valid OrderPostDTO orderPostDTO) {
        DomesResponse domesResponse = orderService.saveOrder(orderPostDTO);

        return new ResponseEntity<>(domesResponse, domesResponse.getCode());
    }

}
