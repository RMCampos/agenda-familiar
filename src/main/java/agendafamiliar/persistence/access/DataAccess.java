package agendafamiliar.persistence.access;

import agendafamiliar.persistence.dao.DataDao;
import agendafamiliar.persistence.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAccess {

    @Autowired
    DataDao dataDao;

    public Data findById(Long data_id) {
        return dataDao.findById(data_id);
    }
}
