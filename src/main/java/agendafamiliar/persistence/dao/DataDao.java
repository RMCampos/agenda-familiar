package agendafamiliar.persistence.dao;

import agendafamiliar.persistence.entity.Calendario;
import agendafamiliar.persistence.entity.Data;
import agendafamiliar.persistence.mapper.DataRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDao extends Dao<Data> {

    public DataDao(NamedParameterJdbcTemplate template) {
        super(Data.class, template, new DataRowMapper());
    }

    public Data findById(Long data_id) {
        final String query = "SELECT * "
                + "FROM " + Data.TABLE + " "
                + "WHERE " + Data.DATA_ID + "=:" + Data.DATA_ID;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(Data.DATA_ID, data_id);

        return getObjectFromResult(query, params);
    }

    public List<Data> findAll() {
        return getListFromResult("SELECT * FROM " + Data.TABLE);
    }

    public Data create(Data data) {
        String query = "INSERT INTO " + Data.TABLE + "("
                + Data.USUARIO_ID
                + "," + Data.CALENDARIO_ID
                + "," + Data.DIA
                + "," + Data.MES
                + "," + Data.ANO
                + "," + Data.DESCRICAO
                + ") values ("
                + ":" + Data.USUARIO_ID
                + ",:" + Data.CALENDARIO_ID
                + ",:" + Data.DIA
                + ",:" + Data.MES
                + ",:" + Data.ANO
                + ",:" + Data.DESCRICAO
                + ")";

        if (executeInsert(query, getParams(data)) == 1) {
            data.setCalendario_id(getGeneratedId(Data.DATA_ID));
            return data;
        }

        return null;
    }

    public Data update(Data data) {
        String query = "UPDATE " + Data.TABLE + " "
                + "SET " + Data.DIA + "=:" + Data.DIA
                + " ," + Data.MES + "=:" + Data.MES
                + " ," + Data.ANO + "=:" + Data.ANO
                + " ," + Data.DESCRICAO + "=:" + Data.DESCRICAO
                + "WHERE " + Data.DATA_ID + "=:" + Data.DATA_ID;

        if (executeUpdateDelete(query, getParams(data)) == 1) {
            return data;
        }

        return null;
    }

    public Data deleteById(Data data) {
        String query = "DELETE FROM " + Data.TABLE
                + " WHERE " + Data.DATA_ID + "=:" + Data.DATA_ID;

        if (executeUpdateDelete(query, getParams(data)) == 1) {
            return data;
        }

        return null;
    }

    private Map<String, Object> getParams(Data data) {
        Map<String, Object> params = new HashMap<>();
        params.put(Data.DATA_ID, data.getData_id());
        params.put(Data.USUARIO_ID, data.getUsuario_id());
        params.put(Data.CALENDARIO_ID, data.getCalendario_id());
        params.put(Data.DIA, data.getDia());
        params.put(Data.MES, data.getMes());
        params.put(Data.ANO, data.getAno());
        params.put(Data.DESCRICAO, data.getDescricao());
        return params;
    }
}
