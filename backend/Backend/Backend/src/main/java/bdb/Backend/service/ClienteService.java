package bdb.Backend.service;

import bdb.Backend.dto.request.ClienteRequest;
import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.repository.ClienteRepository;
import bdb.Backend.util.LogUtils;
import bdb.Backend.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements  IClienteService{


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ResponseApiBuilderService responseApiBuilderService;


    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);


   public ApiResponse<ClienteResponse> byCliente(String tipoDocumento,String numeroDocumento) {
       try {
           ClienteRequest request = new ClienteRequest(tipoDocumento,numeroDocumento);
           List<ValidationUtil.ValidationError> validationErrors = ValidationUtil.validateObject(request);
           if (!validationErrors.isEmpty()) {
               String errorMessage = validationErrors.stream()
                       .map(error -> error.getProperty() + ": " + error.getMessage())
                       .collect(Collectors.joining(", "));

               LogUtils.advertencia(logger, "Error de validación", errorMessage);

               return ResponseApiBuilderService.errorResponse(
                       400,
                       "VALIDATION_ERROR",
                       "Error de validación: " + errorMessage
               );
           }

           LogUtils.inicioOperacion(logger, "búsqueda de cliente",
                   String.format("código=%s, documento=%s", request.getCodigo(), request.getNumeroDocumento()));

           ClienteResponse clienteResponse = this.clienteRepository.byCliente(request.getCodigo(), request.getNumeroDocumento());

           if (clienteResponse != null) {
               LogUtils.finOperacion(logger, "búsqueda de cliente",
                       String.format("cliente=%s %s", clienteResponse.getPrimerNombre(), clienteResponse.getPrimerApellido()));
               return ResponseApiBuilderService.successResponse(clienteResponse, "ClienteVerificado");
           } else {
               LogUtils.advertencia(logger, "Cliente no encontrado",
                       String.format("código=%s, documento=%s", request.getCodigo(), request.getNumeroDocumento()));
               return ResponseApiBuilderService.errorResponse(404, "CLIENT_NOT_FOUND", "Cliente no encontrado");
           }
       } catch (Exception e) {
           LogUtils.error(logger, "Error al buscar cliente", e.getMessage());
           return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "ERROR EN EL SERVIDOR");
       }
   }


}
