package agendafamiliar.dal.dao;

import agendafamiliar.dal.entity.Usuario;
import agendafamiliar.dal.mapper.UsuarioRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioDao extends Dao<Usuario> {

    public UsuarioDao(NamedParameterJdbcTemplate template) {
        super(Usuario.class, template, new UsuarioRowMapper());
    }

    public Usuario findById(Long usuario_id) {
        final String query = "SELECT * FROM " + Usuario.TABLE
            + " WHERE " + Usuario.USUARIOS_ID + "=:" + Usuario.USUARIOS_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Usuario.USUARIOS_ID, usuario_id);

        return getObjectFromResult(query, params);
    }

    public Usuario findByEmail(String email) {
        final String query = "SELECT * FROM " + Usuario.TABLE
            + " WHERE " + Usuario.EMAIL + "=:" + Usuario.EMAIL;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Usuario.EMAIL, email);

        return getObjectFromResult(query, params);
    }

    public List<Usuario> findAll(boolean apenasAtivos) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(Usuario.TABLE);

        if (!apenasAtivos) {
            //sb.append(" WHERE ").append(Usuario.DISABLED_AT).append(" is null");
        }

        return getListFromResult(sb.toString());
    }

    public List<Usuario> findAllByCityId(Long city_id) {
        final String query = "SELECT * FROM " + Usuario.TABLE
            + " WHERE " + Usuario.USUARIOS_ID + "=:" + Usuario.USUARIOS_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Usuario.USUARIOS_ID, city_id);

        return getListFromResult(query, params);
    }

    public Usuario create(Usuario usuario) {
        final String query = "INSERT INTO " + Usuario.TABLE + "("
            + Usuario.NOME
            + "," + Usuario.EMAIL
            + "," + Usuario.SENHA
            + ") values ("
            + ":" + Usuario.NOME
            +",:" + Usuario.EMAIL
            +",:" + Usuario.SENHA
            +")";

        if (executeInsert(query, getParams(usuario)) == 1) {
            usuario.setUsuarios_id(getGeneratedId(Usuario.USUARIOS_ID));
            return usuario;
        }

        return null;
    }

    public Usuario update(Usuario user) {
        final StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(Usuario.TABLE).append(" ");
        sb.append("SET ").append(Usuario.NOME).append("=:").append(Usuario.NOME);
        sb.append(",").append(Usuario.EMAIL).append("=:").append(Usuario.EMAIL);
        sb.append(",").append(Usuario.SENHA).append("=:").append(Usuario.SENHA);
        sb.append(" WHERE ").append(Usuario.USUARIOS_ID).append("=:").append(Usuario.USUARIOS_ID);

        if (executeUpdateDelete(sb.toString(), getParams(user)) == 1) {
            return user;
        }

        return null;
    }

    public Usuario disable(Usuario usuario) {
        usuario.setNome("Desabilitado");

        final String query = "UPDATE " + Usuario.TABLE
            + " SET " + Usuario.NOME + "=:" + Usuario.NOME
            + " WHERE " + Usuario.USUARIOS_ID + "=:" + Usuario.USUARIOS_ID;

        if (executeUpdateDelete(query, getParams(usuario)) == 1) {
            return usuario;
        }

        return null;
    }

    private Map<String, Object> getParams(Usuario usuario) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(Usuario.USUARIOS_ID, usuario.getUsuarios_id());
        params.put(Usuario.NOME, usuario.getNome());
        params.put(Usuario.EMAIL, usuario.getEmail());
        params.put(Usuario.SENHA, usuario.getSenha());
        return params;
    }
}
