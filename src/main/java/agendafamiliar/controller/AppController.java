package agendafamiliar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/criar-conta")
    public String criarConta() {
        return "criar-conta";
    }

    @GetMapping("/acessar-conta")
    public String acessarConta() {
        return "acessar-conta";
    }

    @GetMapping("/resetar-senha")
    public String resetarSenha() {
        return "resetar-conta";
    }

    @GetMapping("/confirmar-reset-senha")
    public String confirmarResetSenha() {
        return "confirmar-reset-conta";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    @GetMapping("/contato")
    public String contato() {
        return "contato";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /* --- */

    @GetMapping("/app")
    public String appIndex() {
        return "app/home";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/acess-denied";
    }
}
