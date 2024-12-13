package bdb.Backend.controller;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.model.TipoDocumentoModel;
import bdb.Backend.service.ReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reference")
@Tag(name = "Referencias", description = "API para gestionar datos de referencia como Tipo Documento")
public class referenceDataController {

    @Autowired
    ReferenceService referenceService;

    @Operation(summary = "Obtener tipos de documento")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lista de tipos de documento obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"Success\", \"statusCode\": 200 }, \"data\": [ { \"codigo\": \"C\", \"descripcion\": \"Cédula de Ciudadanía\" }, { \"codigo\": \"P\", \"descripcion\": \"Pasaporte\" } ], \"error\": null }"
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron tipos de documento",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"NO_CONTENT\", \"statusCode\": 404 }, \"data\": null, \"error\": { \"code\": \"NO_CONTENT\", \"description\": \"No hay registros disponibles\" } }"
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{ \"meta\": { \"message\": \"SERVER_ERROR\", \"statusCode\": 500 }, \"data\": null, \"error\": { \"code\": \"SERVER_ERROR\", \"description\": \"Ocurrió un error interno en el servidor. Por favor, intente más tarde.\" } }"
                            )
                    )
            )
    })
    @GetMapping("/getAllTipoDocumento")
    public ResponseEntity<ApiResponse<List<TipoDocumentoModel>>> getAllTipoDocumento() {
        ApiResponse<List<TipoDocumentoModel>> response = this.referenceService.getAllTipoDocumento();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }

}
