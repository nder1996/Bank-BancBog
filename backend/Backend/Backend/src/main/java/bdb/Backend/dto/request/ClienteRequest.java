package bdb.Backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Objeto de petición para consulta de cliente")
public class ClienteRequest {


    @Schema(
            title = "Código del tipo de documento",
            description = "Código que identifica el tipo de documento. Valores permitidos: C (Cédula), P (Pasaporte)",
            example = "C",
            required = true,
            pattern = "^[CP]$",
            maxLength = 1
    )
    @NotNull(message = "El código no es válido")
    @Size(min = 1, max = 1, message = "El código no es válido")
    @Pattern(regexp = "^[CP]$", message = "El código no es válido")
    @NotBlank(message = "El código es obligatorio y debe ser válido")
    private String codigo;


    @Schema(
            title = "Número de documento",
            description = "Número de documento de identidad del cliente. Solo acepta letras y números",
            example = "23445322",
            required = true,
            pattern = "^[A-Za-z0-9]+$",
            minLength = 1,
            maxLength = 20
    )

    @NotNull(message = "El número de documento no es válido")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "El número de documento no es válido")
    @NotBlank(message = "El número de documento es obligatorio y debe cumplir con el formato esperado.")
    private String numeroDocumento;

    // Constructor vacío
    public ClienteRequest() {}

    // Constructor con todos los argumentos
    public ClienteRequest(String codigo, String numeroDocumento) {
        this.codigo = codigo;
        this.numeroDocumento = numeroDocumento;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    // Método toString (opcional, si es necesario)
    @Override
    public String toString() {
        return "ClienteRequest{" +
                "codigo='" + codigo + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                '}';
    }

}
