package oktenweb.school.dao.functionalDAO;

import oktenweb.school.models.functional.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer> {
    @Override
    void delete(Schedule schedule);

    @Override
    void deleteById(Integer id);

    @Override
    List<Schedule> findAll();
}
