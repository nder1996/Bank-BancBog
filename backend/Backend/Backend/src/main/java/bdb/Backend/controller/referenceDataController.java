package bdb.Backend.controller;

import bdb.Backend.dto.response.ApiResponse;
import bdb.Backend.model.TipoDocumentoModel;
import bdb.Backend.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reference")
public class referenceDataController {

    @Autowired
    ReferenceService referenceService;

    @GetMapping("/getAllTipoDocumento")
    public ResponseEntity<ApiResponse<List<TipoDocumentoModel>>> getAllTipoDocumento() {
        ApiResponse<List<TipoDocumentoModel>> response = this.referenceService.getAllTipoDocumento();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }

}
