package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeachersDAO extends JpaRepository<Teachers, Integer>{
    @Override
    List<Teachers> findAll();

    @Query("select c from Teachers c where c.id = :xxx")
    Teachers byId(@Param("xxx") Integer id);
}
