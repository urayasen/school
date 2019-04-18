package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Teachers;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TeachersService  {
    void save(Teachers teachers);

    List<Teachers> findAll();

    Teachers byId(Integer id);
}
