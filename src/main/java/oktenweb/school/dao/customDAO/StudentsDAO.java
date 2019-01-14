package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentsDAO extends JpaRepository<Students, Integer>{
    @Override
    List<Students> findAll();
}
