package oktenweb.school.service.functionalService;

import oktenweb.school.models.custom.Schedule;

import java.util.List;

public interface ScheduleService {


    void save(Schedule schedule);

    void delete(Schedule schedule);

    void deleteById(Integer id);

    List<Schedule> findAll();

    Schedule byId(Integer id);

    Integer maxById();

    Schedule byName(String name);
}
