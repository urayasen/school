package oktenweb.school.service.customService;

import oktenweb.school.models.custom.Parents;

import java.util.List;

public interface ParentsService {
    void save(Parents parents);

    List<Parents> findAll();

    Parents byId(Integer id);
}
