package bdb.Backend.dto.response;


import bdb.Backend.exception.InvalidApiResponseException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura de respuesta API genérica")
public class ApiResponse<T> {

    @Schema(description = "Metadatos de la respuesta")
    private Meta meta;

    @Schema(description = "Datos de la respuesta")
    private T data;

    @Schema(description = "Detalles del error si existe")
    private ErrorDetails error;

    // Constructors
    public ApiResponse() {
    }

    public ApiResponse(Meta meta, T data, ErrorDetails error) {
        this.meta = meta;
        this.data = data;
        this.error = error;
    }

    // Getters and Setters
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }

    // Inner Classes

    @Schema(description = "Metadatos de la respuesta")
    public static class Meta {

        @Schema(description = "Mensaje de la operación", example = "Operación Exitosa")
        private String message;

        @Schema(description = "Código de estado HTTP", example = "200")
        private Integer statusCode;

        // Constructors
        public Meta() {
        }

        public Meta(String message, Integer statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        // Getters and Setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }
    }

    @Schema(description = "Detalles del error")
    public static class ErrorDetails {

        @Schema(description = "Código del error", example = "SERVER_ERROR")
        private String code;

        @Schema(description = "Descripción del error", example = "ERROR EN EL SERVIDOR")
        private String description;

        // Constructors
        public ErrorDetails() {
        }

        public ErrorDetails(String code, String description) {
            this.code = code;
            this.description = description;
        }

        // Getters and Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
