package agendafamiliar.persistence.mapper;

import agendafamiliar.persistence.entity.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRowMapper implements RowMapper<Data> {

    @Override
    public Data mapRow(ResultSet resultSet, int i) throws SQLException {
        Data data = new Data();
        data.setData_id(resultSet.getLong(Data.DATA_ID));
        data.setUsuario_id(resultSet.getLong(Data.USUARIO_ID));
        data.setCalendario_id(resultSet.getLong(Data.CALENDARIO_ID));
        data.setDia(resultSet.getInt(Data.DIA));
        data.setMes(resultSet.getInt(Data.MES));
        data.setAno(resultSet.getInt(Data.ANO));
        data.setDescricao(resultSet.getString(Data.DESCRICAO));
        return data;
    }
}
