package bdb.Backend.service;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ResponseApiBuilderService responseApiBuilderService;


    private ClienteResponse createValidClienteResponse() {
        ClienteResponse response = new ClienteResponse();
        response.setPrimerNombre("Juan");
        response.setSegundoNombre("Carlos");
        response.setPrimerApellido("Pérez");
        response.setSegundoApellido("García");
        response.setTelefono("987654321");
        response.setDireccion("Av. Principal 123");
        response.setCiudadResidencia("Lima");
        return response;
    }

    @Test
    public void byCliente_WhenValidRequest_ShouldReturnSuccess() {
        // Arrange
        String tipoDocumento = "C";
        String numeroDocumento = "23445322";
        ClienteResponse expectedResponse = createValidClienteResponse();

        when(clienteRepository.byCliente(tipoDocumento, numeroDocumento))
                .thenReturn(expectedResponse);

        // Act
        ApiResponse<ClienteResponse> result = clienteService.byCliente(tipoDocumento, numeroDocumento);

        // Assert
        assertEquals(200, result.getMeta().getStatusCode());
        assertNotNull(result.getData());
        assertEquals("Juan", result.getData().getPrimerNombre());
        assertEquals("Pérez", result.getData().getPrimerApellido());
    }

    @Test
    public void byCliente_WhenClientNotFound_ShouldReturnNotFound() {
        // Arrange
        String tipoDocumento = "C";
        String numeroDocumento = "23445322";

        when(clienteRepository.byCliente(tipoDocumento, numeroDocumento))
                .thenReturn(null);

        // Act
        ApiResponse<ClienteResponse> result = clienteService.byCliente(tipoDocumento, numeroDocumento);

        // Assert
        assertEquals(404, result.getMeta().getStatusCode());
        assertEquals("CLIENT_NOT_FOUND", result.getError().getCode());
        assertNull(result.getData());
    }

    @Test
    public void byCliente_WhenInvalidRequest_ShouldReturnValidationError() {
        // Arrange
        String tipoDocumento = "";  // Valor inválido
        String numeroDocumento = ""; // Valor inválido

        // Act
        ApiResponse<ClienteResponse> result = clienteService.byCliente(tipoDocumento, numeroDocumento);

        // Assert
        assertEquals(400, result.getMeta().getStatusCode());
        assertEquals("VALIDATION_ERROR", result.getError().getCode());
        assertTrue(result.getMeta().getMessage().contains("Bad Request"));
        assertNull(result.getData());
    }

    @Test
    public void byCliente_WhenNullPointerException_ShouldReturn500() {
        // Arrange
        String tipoDocumento = "C";
        String numeroDocumento = "23445322";

        // Hacer que clienteRepository sea null
        clienteService.clienteRepository = null;

        // Act
        ApiResponse<ClienteResponse> result = clienteService.byCliente(tipoDocumento, numeroDocumento);

        // Assert
        assertEquals(500, result.getMeta().getStatusCode());
        assertEquals("SERVER_ERROR", result.getError().getCode());
        assertEquals("ERROR EN EL SERVIDOR", result.getError().getDescription());
    }


}
