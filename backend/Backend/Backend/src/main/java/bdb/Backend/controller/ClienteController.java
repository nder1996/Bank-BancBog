package bdb.Backend.controller;

import bdb.Backend.dto.request.ClienteRequest;
import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cliente")
@Tag(name = "Cliente", description = "API para la busqueda del cliente")
public class ClienteController {

    @Autowired
    private ClienteService clientService;

    @Operation(summary = "Consultar información de un cliente")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"Success\", \"statusCode\": 200 }, \"data\": { \"primerNombre\": \"Juan\", \"segundoNombre\": \"Carlos\", \"primerApellido\": \"Rodríguez\", \"segundoApellido\": \"Gómez\", \"telefono\": \"3157564532\", \"direccion\": \"Calle 123 # 45-67\", \"ciudadResidencia\": \"Bogotá\" }, \"error\": null }"
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos de búsqueda inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"VALIDATION_ERROR\", \"statusCode\": 400 }, \"data\": null, \"error\": { \"code\": \"VALIDATION_ERROR\", \"description\": \"Error de validación: Código o documento inválidos\" } }"
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"CLIENT_NOT_FOUND\", \"statusCode\": 404 }, \"data\": null, \"error\": { \"code\": \"CLIENT_NOT_FOUND\", \"description\": \"El cliente no se encuentra registrado con el código o documento proporcionado\" } }"
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"SERVER_ERROR\", \"statusCode\": 500 }, \"data\": null, \"error\": { \"code\": \"SERVER_ERROR\", \"description\": \"Ocurrió un error interno en el servidor. Por favor, intente más tarde.\" } }"
                            )
                    )
            )
    })
    @Parameters({
            @Parameter(
                    name = "codigo",
                    description = "Tipo de documento del cliente (C: Cédula, P: Pasaporte)",
                    required = true,
                    example = "P"
            ),
            @Parameter(
                    name = "numeroDocumento",
                    description = "Número de documento del cliente",
                    required = true,
                    example = "23445322"
            )
    })
    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<ClienteResponse>> byCliente(
            @RequestParam("codigo") String codigo,
            @RequestParam("numeroDocumento") String numeroDocumento) {
        ApiResponse<ClienteResponse> response = this.clientService.byCliente(codigo,numeroDocumento);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }
}
