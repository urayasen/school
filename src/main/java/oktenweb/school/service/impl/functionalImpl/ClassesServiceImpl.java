package oktenweb.school.service.impl.functionalImpl;

import oktenweb.school.dao.functionalDAO.ClassesDAO;
import oktenweb.school.models.functional.Classes;
import oktenweb.school.service.functionalService.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassesServiceImpl implements ClassesService {
   @Autowired
   ClassesDAO classesDAO;


    @Override
    public void save(Classes classes) {
        classesDAO.save(classes);
    }

    @Override
    public void delete(Classes classes) {
            classesDAO.delete(classes);
    }

    @Override
    public void deleteById(Integer id) {
        classesDAO.deleteById(id);
    }
}