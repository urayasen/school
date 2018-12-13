package oktenweb.school.dao.functionalDAO;

import oktenweb.school.models.functional.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ClassesDAO extends JpaRepository<Classes, Integer> {

    @Override
    void delete(Classes classes);

    @Override
    void deleteById(Integer integer);
}
