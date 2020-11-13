package agendafamiliar.persistence.repository;

import agendafamiliar.persistence.dao.CalendarioDao;
import agendafamiliar.persistence.entity.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarioRepository {

    @Autowired
    CalendarioDao calendarioDao;

    public Calendario findById(Long calendario_id) {
        return calendarioDao.findById(calendario_id);
    }
}
