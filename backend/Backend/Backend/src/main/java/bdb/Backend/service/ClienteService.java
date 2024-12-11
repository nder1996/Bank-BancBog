package bdb.Backend.service;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteService implements  IClienteService{


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ResponseApiBuilderService responseApiBuilderService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);


    @Override
    public ApiResponse<ClienteResponse> byCliente(String codigo, String numeroDocumento) {
        try {
            logger.info("Iniciando búsqueda de cliente - Código: {}, Documento: {}",
                    codigo, numeroDocumento);

            ClienteResponse clienteResponse = this.clienteRepository.byCliente(codigo, numeroDocumento);

            if(clienteResponse != null) {
                logger.info("Cliente encontrado exitosamente");
                return ResponseApiBuilderService.successResponse(clienteResponse, "ClienteVerificado");
            } else {
                logger.info("Cliente no encontrado para código: {} y documento: {}",
                        codigo, numeroDocumento);
                return ResponseApiBuilderService.errorResponse(404, "CLIENT_NOT_FOUND", "Cliente no encontrado");
            }
        } catch (Exception e) {
            logger.error("Error al buscar cliente: {}", e.getMessage(), e);
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "ERROR EN EL SERVIDOR");
        }
    }
}
