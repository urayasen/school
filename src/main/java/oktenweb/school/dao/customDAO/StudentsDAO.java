package oktenweb.school.dao.customDAO;

import oktenweb.school.models.custom.Students;
import oktenweb.school.models.functional.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentsDAO extends JpaRepository<Students, Integer>{
    @Override
    List<Students> findAll();

    @Query("select c from Students c where c.id = :xxx")
    Students byId(@Param("xxx") Integer id);

    @Query("select c from Students c where c.classes.id = :xxx")
    List<Students>  byClassId(@Param("xxx") Integer id);

}


















































































































