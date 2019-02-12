package oktenweb.school.service.impl.functionalImpl;

import oktenweb.school.dao.functionalDAO.SubjectsDAO;
import oktenweb.school.models.functional.Classes;
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
    public void delete(Subjects subjects) {
        subjectsDAO.delete(subjects);
    }

    @Override
    public void deleteById(Integer id) {
        subjectsDAO.deleteById(id);
    }


    @Override
    public List<Subjects> findAll() {
        return subjectsDAO.findAll();
    }

    @Override
    public Integer maxByIdSub() {
        return subjectsDAO.maxByIdSub();    }

//    @Override
//    public Subjects byId(Integer id) {
////        return subjectsDAO.byId(id);
//        return subjectsDAO.byId(id);
//    }

@Override
    public Subjects byIdSub(Integer id){
        return subjectsDAO.byIdSub(id);
}

}
