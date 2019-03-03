package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Teachers;

import java.util.List;

public interface TeachersService {
    void save(Teachers teachers);

    List<Teachers> findAll();

    Teachers byId(Integer id);
}
