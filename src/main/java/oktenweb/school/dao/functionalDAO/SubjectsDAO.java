package oktenweb.school.dao.functionalDAO;

import oktenweb.school.models.functional.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectsDAO extends JpaRepository<Subjects, Integer> {
    @Override
    List<Subjects> findAll();
}
