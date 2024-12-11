package bdb.Backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorExcepcion {
        private final int codigo;
        private final String mensaje;
        private final String detalle;

        public ErrorExcepcion(int codigo, String mensaje, String detalle) {
            this.codigo = codigo;
            this.mensaje = mensaje;
            this.detalle = detalle;
        }

        // Getters
        public int getCodigo() {
            return codigo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public String getDetalle() {
            return detalle;
        }
}
