package agendafamiliar.dal.service;

import agendafamiliar.dal.dao.ContatoDao;
import agendafamiliar.dal.entity.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContatoService {

    @Autowired
    private ContatoDao contatoDao;

    public Contato create(Contato contato) {
        contato.setSent_at(new Date());
        return contatoDao.create(contato);
    }
}
