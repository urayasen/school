package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachersDAO extends JpaRepository<Teachers, Integer>{
    @Override
    List<Teachers> findAll();
}
