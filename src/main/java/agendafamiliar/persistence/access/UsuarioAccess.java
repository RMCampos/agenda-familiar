package agendafamiliar.persistence.access;

import agendafamiliar.persistence.dao.UsuarioDao;
import agendafamiliar.persistence.entity.Usuario;
import agendafamiliar.exception.EntityNotFoundException;
import agendafamiliar.exception.InUseException;
import agendafamiliar.exception.InvalidEmailException;
import agendafamiliar.exception.InvalidFieldException;
import agendafamiliar.util.MailUtil;
import agendafamiliar.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import java.util.List;

@Service
public class UsuarioAccess {

    @Autowired
    private UsuarioDao usuarioDao;

    public Usuario findById(Long id) {
        return usuarioDao.findById(id);
    }

    public Usuario findByEmail(String email) {
        try {
            MailUtil.validateEmailAddress(email);
        } catch (AddressException adre) {
            throw new InvalidEmailException(
                "E-mail inválido!",
                adre,
                adre.getLocalizedMessage()
            );
        }

        return usuarioDao.findByEmail(email);
    }

    public List<Usuario> findAll() {
        return usuarioDao.findAll(false);
    }

    public List<Usuario> findAll(boolean apenasAtivos) {
        return usuarioDao.findAll(apenasAtivos);
    }

    public Usuario create(Usuario usuario) {
        validations(usuario, true);
        usuario.setSenha(PasswordUtil.getSaltedHash(usuario.getSenha()));
        return usuarioDao.create(usuario);
    }

    public Usuario update(Usuario usuario) {
        validations(usuario, false);
        return usuarioDao.update(usuario);
    }

    public Usuario disableById(Long id) {
        Usuario usuario = findById(id);

        if (usuario == null) {
            throw new EntityNotFoundException(Usuario.class, "id", id.toString());
        }

        return usuarioDao.disable(usuario);
    }

    private void validations(Usuario usuario, boolean create) {
        if (!create) {
            if (usuario.getUsuarios_id() == null || usuario.getUsuarios_id().equals(0L)) {
                throw new InvalidFieldException(
                    "Problema ao alterar usuário: id não informado",
                    "Field " + Usuario.USUARIOS_ID + " must not be empty"
                );
            } else {
                Usuario userBd = findById(usuario.getUsuarios_id());
                if (userBd == null) {
                    throw new EntityNotFoundException(
                            Usuario.class,
                            Usuario.USUARIOS_ID,
                            String.valueOf(usuario.getUsuarios_id())
                    );
                }
            }
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: nome não informado",
                "Field " + Usuario.NOME + " must not be empty"
            );
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: e-mail não informado",
                "Field " + Usuario.EMAIL + " must not be empty"
            );
        } else {
            if (create) {
                if (findByEmail(usuario.getEmail()) != null) {
                    throw new InUseException(
                        "Problema ao cadatrar usuário: e-mail já cadastrado",
                        "E-mail already in use"
                    );
                }
            }

            try {
                MailUtil.validateEmailAddress(usuario.getEmail());
            } catch (AddressException adre) {
                throw new InvalidEmailException(
                    "Problema ao cadatrar usuário: e-mail inválido",
                    adre,
                    adre.getLocalizedMessage()
                );
            }
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: senha não informado",
                "Field " + Usuario.SENHA + " must not be empty"
            );
        }
    }
}
