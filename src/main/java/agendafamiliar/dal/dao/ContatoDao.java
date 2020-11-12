package agendafamiliar.dal.dao;

import agendafamiliar.dal.entity.Contato;
import agendafamiliar.dal.mapper.ContactRowMapper;
import agendafamiliar.util.DateUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContatoDao extends Dao<Contato> {

    public ContatoDao(NamedParameterJdbcTemplate template) {
        super(Contato.class, template, new ContactRowMapper());
    }

    public Contato findById(Long contact_id) {
        final String query = "SELECT * "
            + "FROM " + Contato.TABLE + " "
            + "WHERE " + Contato.CONTACT_ID + "=:" + Contato.CONTACT_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Contato.CONTACT_ID, contact_id);

        return getObjectFromResult(query, params);
    }

    public List<Contato> findAll() {
        return getListFromResult("SELECT * FROM " + Contato.TABLE);
    }

    public Contato create(Contato contato) {
        String query = "INSERT INTO " + Contato.TABLE + "("
            + Contato.NAME
            + "," + Contato.EMAIL
            + "," + Contato.SUBJECT
            + "," + Contato.MESSAGE
            + "," + Contato.SENT_AT
            + ") values ("
            + ":" + Contato.NAME
            + ",:" + Contato.EMAIL
            + ",:" + Contato.SUBJECT
            + ",:" + Contato.MESSAGE
            + ",:" + Contato.SENT_AT
            + ")";

        if (executeInsert(query, getParams(contato)) == 1) {
            contato.setContact_id(getGeneratedId(Contato.CONTACT_ID));
            return contato;
        }

        return null;
    }

    public Contato update(Contato contato) {
        String query = "UPDATE " + Contato.TABLE
            + " SET " + Contato.NAME + "=:" + Contato.NAME
            + "," + Contato.EMAIL + "=:" + Contato.EMAIL
            + "," + Contato.SUBJECT + "=:" + Contato.SUBJECT
            + "," + Contato.MESSAGE + "=:" + Contato.MESSAGE
            + "," + Contato.SENT_AT + "=:" + Contato.SENT_AT
            + " WHERE " + Contato.CONTACT_ID + "=:" + Contato.CONTACT_ID;

        if (executeUpdateDelete(query, getParams(contato)) == 1) {
            return contato;
        }

        return null;
    }

    public Contato deleteById(Contato contato) {
        String query = "DELETE FROM " + Contato.TABLE
            + " WHERE " + Contato.CONTACT_ID + "=:" + Contato.CONTACT_ID;

        if (executeUpdateDelete(query, getParams(contato)) == 1) {
            return contato;
        }

        return null;
    }

    private Map<String, Object> getParams(Contato contato) {
        Map<String, Object> params = new HashMap<>();
        params.put(Contato.CONTACT_ID, contato.getContact_id());
        params.put(Contato.NAME, contato.getName());
        params.put(Contato.EMAIL, contato.getEmail());
        params.put(Contato.SUBJECT, contato.getSubject());
        params.put(Contato.MESSAGE, contato.getMessage());
        params.put(Contato.SENT_AT, DateUtil.getGmtTimestamp(contato.getSent_at()));
        return params;
    }
}
