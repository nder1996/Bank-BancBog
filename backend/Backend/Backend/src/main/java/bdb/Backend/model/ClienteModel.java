package bdb.Backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteModel {

    private Integer id;
    private Integer idTipoDocumento;
    private String  numeroDocumento;
    private String  primerNombre;
    private String  segundoNombre;
    private String  primerApellido;
    private String  segundoApellido;
    private String  telefono;
    private String  direccion;
    private String  ciudadResidencia;
}
