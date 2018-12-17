package oktenweb.school.dao.functionalDAO;

import oktenweb.school.models.User;
import oktenweb.school.models.functional.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ClassesDAO extends JpaRepository<Classes, Integer> {

    @Override
    void delete(Classes classes);

    @Override
    void deleteById(Integer id);

    @Override
    List<Classes> findAll();

    @Query("select c from Classes c where c.id = :xxx")
    Classes byId(@Param("xxx") Integer id);


}
