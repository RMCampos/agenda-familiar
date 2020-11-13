package agendafamiliar.persistence.mapper;

import agendafamiliar.persistence.entity.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsuarios_id(resultSet.getLong(Usuario.USUARIOS_ID));
        usuario.setNome(resultSet.getString(Usuario.NOME));
        usuario.setEmail(resultSet.getString(Usuario.EMAIL));
        usuario.setSenha(resultSet.getString(Usuario.SENHA));
        return usuario;
    }
}
