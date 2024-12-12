package bdb.Backend.repository;

import bdb.Backend.model.TipoDocumentoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReferenceDataRepository {

    @Select("select * from tipo_documento where estado = 'A' ")
    List<TipoDocumentoModel> getAllTipoDocumento();

}
