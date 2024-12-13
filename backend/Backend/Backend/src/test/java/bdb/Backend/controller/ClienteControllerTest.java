package bdb.Backend.controller;

import bdb.Backend.dto.request.ClienteRequest;
import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.service.ClienteService;
import bdb.Backend.service.ResponseApiBuilderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteResponse createSampleResponse() {
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
    public void byCliente_WhenValidParameters_ShouldReturnOk() throws Exception {
        // Arrange
        String codigo = "C";
        String numeroDocumento = "23445322";
        ClienteResponse clienteResponse = createSampleResponse();

        ApiResponse<ClienteResponse> apiResponse =
                ResponseApiBuilderService.successResponse(clienteResponse, "ClienteVerificado");

        when(clientService.byCliente(codigo, numeroDocumento))
                .thenReturn(apiResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/cliente/consulta")
                        .param("codigo", codigo)
                        .param("numeroDocumento", numeroDocumento)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.primerNombre").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.segundoNombre").value("Carlos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.primerApellido").value("Pérez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.segundoApellido").value("García"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.telefono").value("987654321"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.direccion").value("Av. Principal 123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.ciudadResidencia").value("Lima"));
    }

}
