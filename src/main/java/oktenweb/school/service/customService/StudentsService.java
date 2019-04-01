package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Students;
import oktenweb.school.models.functional.Classes;

import java.util.List;

public interface StudentsService {
    void save(Students students);

    List<Students> findAll();

    Students byId(Integer id);

    List<Students>  byClassId(Integer id);


}
