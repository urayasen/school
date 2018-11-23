package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersDAO extends JpaRepository<Teachers, Integer>{
}
