package oktenweb.school.service.impl.functionalImpl;

import oktenweb.school.dao.functionalDAO.ScheduleDAO;
import oktenweb.school.models.custom.Schedule;
import oktenweb.school.service.functionalService.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDAO scheduleDAO;

    @Override
    public void save(Schedule schedule) {

    }

    @Override
    public void delete(Schedule schedule) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Schedule> findAll() {
        return null;
    }

    @Override
    public Schedule byId(Integer id) {
        return null;
    }

    @Override
    public Integer maxById() {
        return null;
    }

    @Override
    public Schedule byName(String name) {
        return null;
    }
}
