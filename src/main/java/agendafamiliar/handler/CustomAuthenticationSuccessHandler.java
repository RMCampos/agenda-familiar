package agendafamiliar.handler;

import agendafamiliar.dal.entity.Usuario;
import agendafamiliar.dal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Usuario usuario = usuarioService.findByEmail(authentication.getName());
        if (usuario != null) {
            //user.setLast_login(new Date());
            //usuarioService.update(user);
        }

        response.sendRedirect("/app");
    }
}
