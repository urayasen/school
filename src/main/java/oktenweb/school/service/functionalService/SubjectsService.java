package oktenweb.school.service.functionalService;

import oktenweb.school.models.functional.Classes;
import oktenweb.school.models.functional.Subjects;

import java.util.List;

public interface SubjectsService {

    void save(Subjects subjects);

    void delete(Subjects subjects);

    void deleteById(Integer id);

    List<Subjects> findAll();

    Integer maxByIdSub();

    Subjects byIdSub(Integer id);
}
