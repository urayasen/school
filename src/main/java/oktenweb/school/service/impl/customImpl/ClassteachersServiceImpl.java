package oktenweb.school.service.impl.customImpl;

import oktenweb.school.dao.customDAO.ClassteachersDAO;
import oktenweb.school.models.custom.Classteachers;
import oktenweb.school.service.customService.ClassteachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassteachersServiceImpl implements ClassteachersService{

    @Autowired
    ClassteachersDAO classteachersDAO;

    @Override
    public void save(Classteachers classteachers){
        classteachersDAO.save(classteachers);
    }

    @Override
    public List<Classteachers> findAll() {
        return classteachersDAO.findAll();
    }

    @Override
    public Classteachers byId(Integer id) {
        return classteachersDAO.byId(id);
    }
}
