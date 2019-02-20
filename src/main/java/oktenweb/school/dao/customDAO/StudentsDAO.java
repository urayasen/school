package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsDAO extends JpaRepository<Students, Integer>{
    @Override
    List<Students> findAll();

    @Query("select c from Students c where c.name = :xxx")
    Students byName(@Param("xxx") String name);
}
