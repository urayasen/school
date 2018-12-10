package oktenweb.school.models.functional;

import java.util.ArrayList;
import java.util.List;

public  class ListSubjects {

    private List<String> listSubjectres = new ArrayList<>();

    public void addSubjects(){
        listSubjectres.add("Математика");
        listSubjectres.add("Фізика");
        listSubjectres.add("Укр. мова");
        listSubjectres.add("Укр. література");
        listSubjectres.add("Англійська мова");
        listSubjectres.add("Геометрія");
        listSubjectres.add("Географія");
    }

    public List<String> getListSubjectres() {
        return listSubjectres;
    }

    public void setListSubjectres(List<String> listSubjectres) {
        this.listSubjectres = listSubjectres;
    }
}
