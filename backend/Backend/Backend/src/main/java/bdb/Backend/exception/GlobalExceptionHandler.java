package bdb.Backend.exception;

import bdb.Backend.dto.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorExcepcion> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        try {
            logger.error("Error al procesar el JSON: ", ex);
            ErrorExcepcion error = new ErrorExcepcion(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error en el formato del JSON o campos inválidos",
                    "El cuerpo de la petición no tiene el formato esperado"
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error inesperado al procesar HttpMessageNotReadableException: ", e);
            ErrorExcepcion errorFallback = new ErrorExcepcion(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error interno del servidor",
                    "Ocurrió un error inesperado al procesar la solicitud"
            );
            return new ResponseEntity<>(errorFallback, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex) {
        try {
            logger.error("Error global no controlado: ", ex);

            String developerMessage = Optional.ofNullable(ex.getMessage())
                    .orElse("No hay detalles adicionales disponibles");

            ApiResponse<Object> response = buildErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "INTERNAL_SERVER_ERROR",
                    "Se ha producido un error interno en el servidor",
                    developerMessage
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            logger.error("Error crítico en el manejador global de excepciones: ", e);
            return createFallbackResponse();
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(ResourceNotFoundException ex) {
        try {
            logger.error("Recurso no encontrado: ", ex);

            ApiResponse<Object> response = buildErrorResponse(
                    HttpStatus.NOT_FOUND,
                    "RESOURCE_NOT_FOUND",
                    "El recurso solicitado no existe",
                    ex.getMessage()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            logger.error("Error al procesar ResourceNotFoundException: ", e);
            return createFallbackResponse();
        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(ValidationException ex) {
        try {
            logger.error("Error de validación: ", ex);

            ApiResponse<Object> response = buildErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "VALIDATION_ERROR",
                    "Error en los datos proporcionados",
                    ex.getMessage()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.error("Error al procesar ValidationException: ", e);
            return createFallbackResponse();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        try {
            logger.error("Error de validación de argumentos: ", ex);

            String errorMessage = extractValidationErrors(ex);

            ApiResponse<Object> response = buildErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "VALIDATION_ERROR",
                    "Error en los datos proporcionados",
                    errorMessage
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.error("Error al procesar MethodArgumentNotValidException: ", e);
            return createFallbackResponse();
        }
    }

    private String extractValidationErrors(MethodArgumentNotValidException ex) {
        try {
            return ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } catch (Exception e) {
            logger.error("Error al extraer mensajes de validación: ", e);
            return "Error al procesar los detalles de la validación";
        }
    }

    private ApiResponse<Object> buildErrorResponse(
            HttpStatus httpStatus,
            String errorCode,
            String userMessage,
            String developerMessage) {
        try {
            return new ApiResponse<>(
                    new ApiResponse.Meta(null, null),
                    null,
                    new ApiResponse.ErrorDetails(errorCode,
                            developerMessage != null ? developerMessage : userMessage)
            );
        } catch (Exception e) {
            logger.error("Error al construir la respuesta de error: ", e);
            return createFallbackErrorResponse();
        }
    }

    private ResponseEntity<ApiResponse<Object>> createFallbackResponse() {
        ApiResponse<Object> fallbackResponse = new ApiResponse<>(
                new ApiResponse.Meta(null, null),
                null,
                new ApiResponse.ErrorDetails(
                        "CRITICAL_ERROR",
                        "Se ha producido un error crítico en el sistema"
                )
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(fallbackResponse);
    }

    private ApiResponse<Object> createFallbackErrorResponse() {
        return new ApiResponse<>(
                new ApiResponse.Meta(null, null),
                null,
                new ApiResponse.ErrorDetails(
                        "CRITICAL_ERROR",
                        "Error al generar la respuesta de error"
                )
        );
    }

}

