package agendafamiliar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contato")
    public String contato() {
        return "contato";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /* --- */

    @GetMapping("/app")
    public String appIndex() {
        return "app/index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/acess-denied";
    }
}
