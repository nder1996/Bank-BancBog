package bdb.Backend.service;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.model.TipoDocumentoModel;
import bdb.Backend.repository.ReferenceDataRepository;
import bdb.Backend.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceService {

    private static final Logger log = LoggerFactory.getLogger(ReferenceService.class);

    @Autowired
    ReferenceDataRepository referenceRepository;


    public ApiResponse<List<TipoDocumentoModel>> getAllTipoDocumento() {
        try {
            LogUtils.inicioOperacion(log, "obtener todos los tipos de documento", "");

            List<TipoDocumentoModel> tipoDocumento = this.referenceRepository.getAllTipoDocumento();

            if (tipoDocumento != null && !tipoDocumento.isEmpty()) {
                LogUtils.finOperacion(log, "tipos de documento encontrados",
                        String.format("cantidad=%d", tipoDocumento.size()));
                return ResponseApiBuilderService.successResponse(tipoDocumento, "TIPO_DOCUMENTO");
            } else {
                LogUtils.advertencia(log, "tipos de documento", "No hay registros disponibles");
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");
            }
        } catch (Exception e) {
            LogUtils.error(log, "Error al obtener tipos de documento", e.getMessage());
            return ResponseApiBuilderService.errorResponse(
                    500,
                    "SERVER_ERROR",
                    "ERROR EN EL SERVIDOR"
            );
        }
    }

}
