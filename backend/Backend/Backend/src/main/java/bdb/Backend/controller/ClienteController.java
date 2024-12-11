package bdb.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {


    @GetMapping("/prueba")
    public ResponseEntity<String> getAllGestionTareas() {
        return ResponseEntity.status(200).body("response");
    }
}
