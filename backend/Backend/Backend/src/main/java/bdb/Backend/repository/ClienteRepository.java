package bdb.Backend.repository;

import bdb.Backend.model.ClienteModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClienteRepository {

    @Select("SELECT " +
            "1 as id, " +
            "1 as id_tipo_documento, " +
            "'23445322' as numero_documento, " +
            "'Juan' as primer_nombre, " +
            "'Carlos' as segundo_nombre, " +
            "'Rodríguez' as primer_apellido, " +
            "'Gómez' as segundo_apellido, " +
            "'3157564532' as telefono, " +
            "'Calle 123 # 45-67' as direccion, " +
            "'Bogotá' as ciudad_residencia " +
            "FROM dual " +
            "WHERE id_tipo_documento = #{idTipoDocumento} " +
            "AND numero_documento = #{numeroDocumento}")
    ClienteModel findByTipoAndNumeroDocumento(
            @Param("idTipoDocumento") Integer idTipoDocumento,
            @Param("numeroDocumento") String numeroDocumento
    );


}
