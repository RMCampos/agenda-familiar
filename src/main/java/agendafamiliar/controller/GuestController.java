package agendafamiliar.controller;

import agendafamiliar.persistence.entity.Usuario;
import agendafamiliar.persistence.access.UsuarioAccess;
import agendafamiliar.exception.InvalidEmailException;
import agendafamiliar.service.MailService;
import agendafamiliar.util.MailUtil;
import agendafamiliar.vo.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UsuarioAccess usuarioService;

    @GetMapping(value = "/email-available/{email}")
    public Boolean isEmailAvailable(@PathVariable("email") String email) {
        return usuarioService.findByEmail(email) == null;
    }

    @PostMapping(value = "/criar-usuario")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @PostMapping(value = "/contato", produces = "application/json")
    public Contato send(@RequestBody Contato contato) {
        try {
            MailUtil.validateEmailAddress(contato.getEmail());
        } catch (AddressException adress) {
            throw new InvalidEmailException(
                "E-mail inválido!",
                adress,
                adress.getLocalizedMessage()
            );
        }

        mailService.send(contato);

        return contato;
    }
}
