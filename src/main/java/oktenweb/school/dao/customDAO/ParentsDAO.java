package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentsDAO extends JpaRepository<Parents, Integer>{
    @Override
    List<Parents> findAll();
}
