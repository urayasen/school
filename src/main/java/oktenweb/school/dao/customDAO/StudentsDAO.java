package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsDAO extends JpaRepository<Students, Integer>{
}
