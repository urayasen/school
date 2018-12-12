package oktenweb.school.service.impl.customImpl;

import oktenweb.school.dao.customDAO.TeachersDAO;
import oktenweb.school.models.custom.Teachers;
import oktenweb.school.service.customService.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeachersServiceImpl implements TeachersService {

    @Autowired
    TeachersDAO teachersDAO;

    @Override
    public void save(Teachers teachers) {
        teachersDAO.save(teachers);
    }
}
