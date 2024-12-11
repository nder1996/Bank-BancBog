package bdb.Backend.controller;

import bdb.Backend.dto.request.ClienteRequest;
import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clientService;

    @GetMapping("/prueba")
    public ResponseEntity<String> getAllGestionTareas() {
        return ResponseEntity.status(200).body("response");
    }


    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<ClienteResponse>> byCliente(@RequestBody ClienteRequest request) {
        ApiResponse<ClienteResponse> response = this.clientService.byCliente(request);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }
}
