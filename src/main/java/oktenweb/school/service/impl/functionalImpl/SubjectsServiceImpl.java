package oktenweb.school.service.impl.functionalImpl;

import oktenweb.school.dao.functionalDAO.SubjectsDAO;
import oktenweb.school.models.functional.Subjects;
import oktenweb.school.service.functionalService.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsServiceImpl implements SubjectsService {

    @Autowired
    SubjectsDAO subjectsDAO;

    @Override
    public void save(Subjects subjects) {
        subjectsDAO.save(subjects);
    }

    @Override
    public List<Subjects> findAll() {
        return subjectsDAO.findAll();
    }
}
