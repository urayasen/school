package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Classteachers;
import oktenweb.school.models.custom.Students;
import oktenweb.school.models.functional.Classes;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClassteachersService  {
    void save(Classteachers classteachers);

    List<Classteachers> findAll();

    Classteachers byId(Integer id);
}
