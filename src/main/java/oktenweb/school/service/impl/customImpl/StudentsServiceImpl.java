package oktenweb.school.service.impl.customImpl;


import oktenweb.school.dao.customDAO.StudentsDAO;
import oktenweb.school.models.custom.Students;
import oktenweb.school.service.customService.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsServiceImpl implements StudentsService{

    @Autowired
    StudentsDAO studentsDAO;

    @Override
    public void save(Students students) {
        studentsDAO.save(students);
    }
}
