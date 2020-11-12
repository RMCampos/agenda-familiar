package agendafamiliar.dal.dao;

import agendafamiliar.dal.entity.Calendario;
import agendafamiliar.dal.mapper.CalendarioRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarioDao extends Dao<Calendario> {

    public CalendarioDao(NamedParameterJdbcTemplate template) {
        super(Calendario.class, template, new CalendarioRowMapper());
    }

    public Calendario findById(Long calendario_id) {
        final String query = "SELECT * "
                + "FROM " + Calendario.TABLE + " "
                + "WHERE " + Calendario.CALENDARIO_ID + "=:" + Calendario.CALENDARIO_ID;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue(City.CITY_ID, calendario_id);

        return getObjectFromResult(query, params);
    }

    public List<Calendario> findAll() {
        return getListFromResult("SELECT * FROM " + Calendario.TABLE);
    }

    public Calendario create(Calendario calendario) {
        String query = "INSERT INTO " + Calendario.TABLE + "("
                + Calendario.USUARIO_ID
                + "," + Calendario.NOME
                + ") values ("
                + ":" + Calendario.USUARIO_ID
                + ",:" + Calendario.NOME
                + ")";

        if (executeInsert(query, getParams(calendario)) == 1) {
            calendario.setCalendario_id(getGeneratedId(Calendario.CALENDARIO_ID));
            return calendario;
        }

        return null;
    }

    public Calendario update(Calendario calendario) {
        String query = "UPDATE " + Calendario.TABLE + " "
                + "SET " + Calendario.NOME + "=:" + Calendario.NOME
                + "WHERE " + Calendario.CALENDARIO_ID + "=:" + Calendario.CALENDARIO_ID;

        if (executeUpdateDelete(query, getParams(calendario)) == 1) {
            return calendario;
        }

        return null;
    }

    public Calendario deleteById(Calendario calendario) {
        String query = "DELETE FROM " + Calendario.TABLE
                + " WHERE " + Calendario.CALENDARIO_ID + "=:" + Calendario.CALENDARIO_ID;

        if (executeUpdateDelete(query, getParams(calendario)) == 1) {
            return calendario;
        }

        return null;
    }

    private Map<String, Object> getParams(Calendario calendario) {
        Map<String, Object> params = new HashMap<>();
        params.put(Calendario.CALENDARIO_ID, calendario.getCalendario_id());
        params.put(Calendario.USUARIO_ID, calendario.getUsuario_id());
        params.put(Calendario.NOME, calendario.getNome());
        return params;
    }
}
