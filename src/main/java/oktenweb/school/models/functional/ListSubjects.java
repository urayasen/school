package oktenweb.school.models.functional;

import java.util.ArrayList;
import java.util.List;

public  class ListSubjects {

    private List<String> listSubjectres = new ArrayList<>();

    public void addSubjects(){

        listSubjectres.add("Painting");
        listSubjectres.add("Reading");
        listSubjectres.add("Writing");
        listSubjectres.add("Mathematics");
        listSubjectres.add("Music");
    }

    public List<String> getListSubjectres() {
        return listSubjectres;
    }

    public void setListSubjectres(List<String> listSubjectres) {
        this.listSubjectres = listSubjectres;
    }
}
