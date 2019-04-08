package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Deputy;
import oktenweb.school.models.custom.Teachers;

import java.util.List;

public interface DeputyService {
    void save(Deputy deputy);

    List<Deputy> findAll();

    Deputy byId(Integer id);



}
