package bdb.Backend.service;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.dto.response.ClienteResponse;

public interface IClienteService {

    public ApiResponse<ClienteResponse> byCliente(String codigo , String numeroDocumento);
}
