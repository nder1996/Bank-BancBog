package bdb.Backend.repository;

import bdb.Backend.dto.response.ClienteResponse;
import bdb.Backend.model.ClienteModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClienteRepository {

    @Select("SELECT " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN 'Juan' " +
            "    ELSE NULL " +
            "END as primerNombre, " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN 'Carlos' " +
            "    ELSE NULL " +
            "END as segundoNombre, " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN 'Rodríguez' " +
            "    ELSE NULL " +
            "END as primerApellido, " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN 'Gómez' " +
            "    ELSE NULL " +
            "END as segundoApellido, " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN '3157564532' " +
            "    ELSE NULL " +
            "END as telefono, " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN 'Calle 123 # 45-67' " +
            "    ELSE NULL " +
            "END as direccion, " +
            "CASE " +
            "    WHEN #{idTipoDocumento} = 'C' AND #{numeroDocumento} = '23445322' THEN 'Bogotá' " +
            "    ELSE NULL " +
            "END as ciudadResidencia " +
            "FROM dual")
    ClienteResponse byCliente(
            @Param("idTipoDocumento") String idTipoDocumento,
            @Param("numeroDocumento") String numeroDocumento
    );


}
