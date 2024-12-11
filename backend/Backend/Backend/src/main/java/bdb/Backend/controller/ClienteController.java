package bdb.Backend.controller;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ApiResponse<ClienteResponse>> byCliente(@Validated @RequestParam(required = true) String tipoDocumento,
                                                                  @Validated @RequestParam(required = true) String numeroDocumento) {
        ApiResponse<ClienteResponse> response = this.clientService.byCliente(tipoDocumento,numeroDocumento);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }
}
