package bdb.Backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotNull(message = "El código es obligatorio")
    @Size(min = 1, max = 1, message = "El código debe ser de un solo carácter")
    @Pattern(regexp = "[A-Za-z]", message = "El código solo puede ser una letra")
    private String codigo;



    @NotNull(message = "El número de documento es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "El documento solo puede contener letras y números")
    private String numeroDocumento;

}
