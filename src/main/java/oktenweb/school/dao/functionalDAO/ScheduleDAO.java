package oktenweb.school.dao.functionalDAO;

import oktenweb.school.models.custom.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule, Integer> {


    @Override
    void delete(Schedule schedule);

    @Override
    void deleteById(Integer id);

    @Override
    List<Schedule> findAll();

    @Query("select c from Schedule c where c.id = :xxx")
    Schedule byId(@Param("xxx") Integer id);

    @Query("select max(c.id) from Schedule c" )
    Integer maxById();

//    @Query("select c from Schedule c where c.name = :xxx")
//    Schedule byName(@Param("xxx") String name);
}
