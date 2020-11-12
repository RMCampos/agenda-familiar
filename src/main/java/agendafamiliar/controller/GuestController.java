package agendafamiliar.controller;

import agendafamiliar.dal.entity.Contato;
import agendafamiliar.dal.entity.Usuario;
import agendafamiliar.dal.service.ContatoService;
import agendafamiliar.dal.service.UsuarioService;
import agendafamiliar.exception.InvalidEmailException;
import agendafamiliar.service.MailService;
import agendafamiliar.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/email-available/{email}")
    public Boolean isEmailAvailable(@PathVariable("email") String email) {
        return usuarioService.findByEmail(email) == null;
    }

    @PostMapping(value = "/criar-usuario")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    // Contact form URLs
    @PostMapping(value = "/contat0", produces = "application/json")
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
        contatoService.create(contato);

        return contato;
    }
}
