package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Classteachers;
import oktenweb.school.models.functional.Classes;

import java.util.List;

public interface ClassteachersService {
    void save(Classteachers classteachers);

    List<Classteachers> findAll();
}
