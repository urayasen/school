package oktenweb.school.service.impl.functionalImpl;

import oktenweb.school.dao.functionalDAO.ClassJournalDAO;
import oktenweb.school.models.functional.ClassJournal;
import oktenweb.school.service.functionalService.ClassJournalService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassJournalServiceImpl implements ClassJournalService {
    @Autowired
    ClassJournalDAO classJournalDAO;

    @Override
    public void save(ClassJournal classJournal) {
        classJournalDAO.save(classJournal);
    }
}
