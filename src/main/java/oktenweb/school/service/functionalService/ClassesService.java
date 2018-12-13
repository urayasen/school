package oktenweb.school.service.functionalService;

import oktenweb.school.models.functional.Classes;

public interface ClassesService {

    void save(Classes classes);

    void delete(Classes classes);

    void deleteById(Integer id);
}
