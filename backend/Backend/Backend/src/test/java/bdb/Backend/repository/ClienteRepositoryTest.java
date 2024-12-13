package bdb.Backend.repository;

import bdb.Backend.dto.response.ClienteResponse;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteRepositoryTest {


    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void byCliente_WhenValidParameters_ShouldReturnClientData() {
        // When
        ClienteResponse cliente = clienteRepository.byCliente("C", "23445322");

        // Then
        assertAll(
                () -> assertNotNull(cliente, "La respuesta no debe ser null"),
                () -> assertEquals("Juan", cliente.getPrimerNombre()),
                () -> assertEquals("Carlos", cliente.getSegundoNombre()),
                () -> assertEquals("Rodríguez", cliente.getPrimerApellido()),
                () -> assertEquals("Gómez", cliente.getSegundoApellido()),
                () -> assertEquals("3157564532", cliente.getTelefono()),
                () -> assertEquals("Calle 123 # 45-67", cliente.getDireccion()),
                () -> assertEquals("Bogotá", cliente.getCiudadResidencia())
        );
    }






}
