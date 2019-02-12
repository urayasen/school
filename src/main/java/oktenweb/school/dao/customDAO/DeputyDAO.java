package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Deputy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeputyDAO extends JpaRepository<Deputy, Integer>{
    @Override
    List<Deputy> findAll();
}
