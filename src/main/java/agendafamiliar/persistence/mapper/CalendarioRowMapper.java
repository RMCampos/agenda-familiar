package agendafamiliar.persistence.mapper;

import agendafamiliar.persistence.entity.Calendario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarioRowMapper implements RowMapper<Calendario> {

    @Override
    public Calendario mapRow(ResultSet resultSet, int i) throws SQLException {
        Calendario calendario = new Calendario();
        calendario.setCalendario_id(resultSet.getLong(Calendario.CALENDARIO_ID));
        calendario.setUsuario_id(resultSet.getLong(Calendario.USUARIO_ID));
        calendario.setNome(resultSet.getString(Calendario.NOME));
        return calendario;
    }
}
