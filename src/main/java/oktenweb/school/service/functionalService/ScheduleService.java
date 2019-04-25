package oktenweb.school.service.functionalService;

import oktenweb.school.models.functional.Schedule;

import java.util.List;

public interface ScheduleService {

    void save(Schedule schedule);

    void delete(Schedule schedule);

    List<Schedule> findAll();

    Schedule byId (Integer id);

}
