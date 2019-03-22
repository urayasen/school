package oktenweb.school.service.impl.customImpl;


import oktenweb.school.dao.customDAO.StudentsDAO;
import oktenweb.school.models.custom.Students;
import oktenweb.school.models.functional.Classes;
import oktenweb.school.service.customService.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService{

    @Autowired
    StudentsDAO studentsDAO;

    @Override
    public void save(Students students) {
        studentsDAO.save(students);
    }

    @Override
    public List<Students> findAll() {
        return studentsDAO.findAll();
    }

    @Override
    public Students byId(Integer id) {
        return studentsDAO.byId(id);
    }

    @Override
    public List<Students> byClassId(Integer id) {
        return studentsDAO.byClassId(id);
    }


}
