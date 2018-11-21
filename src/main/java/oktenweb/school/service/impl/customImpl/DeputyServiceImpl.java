package oktenweb.school.service.impl.customImpl;

import oktenweb.school.dao.customDAO.DeputyDAO;
import oktenweb.school.models.custom.Deputy;
import oktenweb.school.service.customService.DeputyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeputyServiceImpl implements DeputyService{

    @Autowired
    DeputyDAO deputyDAO;

    @Override
    public void save(Deputy deputy){
        deputyDAO.save(deputy);
    }
}
