package oktenweb.school.service.impl.customImpl;

import oktenweb.school.dao.customDAO.ParentsDAO;
import oktenweb.school.models.custom.Parents;
import oktenweb.school.service.customService.ParentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentsServiceImpl implements ParentsService{

    @Autowired
    ParentsDAO parentsDAO;

    @Override
    public void save(Parents parents){
        parentsDAO.save(parents);
    }
}
