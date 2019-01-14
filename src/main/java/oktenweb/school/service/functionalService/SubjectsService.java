package oktenweb.school.service.functionalService;

import oktenweb.school.models.functional.Subjects;

import java.util.List;

public interface SubjectsService {

    void save(Subjects subjects);

    List<Subjects> findAll();
}
