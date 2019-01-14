package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Classteachers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassteachersDAO extends JpaRepository<Classteachers, Integer>{
    @Override
    List<Classteachers> findAll();
}
