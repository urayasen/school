package oktenweb.school.models.functional;

import java.util.ArrayList;
import java.util.List;

public  class ListSubjects {

    private List<String> listSubjectres = new ArrayList<>();

    public void addSubjects(){
        listSubjectres.add("Matematyka");
        listSubjectres.add("Phizyka");
        listSubjectres.add("Ykrmova");
        listSubjectres.add("Ykrliteratura");
        listSubjectres.add("Angliskamova");
        listSubjectres.add("Heometria");
        listSubjectres.add("Heographia");
    }

    public List<String> getListSubjectres() {
        return listSubjectres;
    }

    public void setListSubjectres(List<String> listSubjectres) {
        this.listSubjectres = listSubjectres;
    }
}
