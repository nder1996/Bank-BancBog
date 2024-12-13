package bdb.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
public class TipoDocumentoModel {

    private String codigo;
    private String descripcion;

    // Constructor sin argumentos
    public TipoDocumentoModel() {
    }

    // Constructor con argumentos
    public TipoDocumentoModel(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoDocumentoModel that = (TipoDocumentoModel) o;
        return Objects.equals(codigo, that.codigo) &&
                Objects.equals(descripcion, that.descripcion);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(codigo, descripcion);
    }

    // Método toString
    @Override
    public String toString() {
        return "TipoDocumentoModel{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }


}
