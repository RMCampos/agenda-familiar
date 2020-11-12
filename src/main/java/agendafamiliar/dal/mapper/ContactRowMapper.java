package agendafamiliar.dal.mapper;

import agendafamiliar.dal.entity.Contato;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ContactRowMapper implements RowMapper<Contato> {

    @Override
    public Contato mapRow(ResultSet resultSet, int i) throws SQLException {
        Contato contato = new Contato();
        contato.setContact_id(resultSet.getLong(Contato.CONTACT_ID));
        contato.setName(resultSet.getString(Contato.NAME));
        contato.setEmail(resultSet.getString(Contato.EMAIL));
        contato.setSubject(resultSet.getString(Contato.SUBJECT));
        contato.setMessage(resultSet.getString(Contato.MESSAGE));
        contato.setSent_at(new Date(resultSet.getTimestamp(Contato.SENT_AT).getTime()));
        return contato;
    }
}
