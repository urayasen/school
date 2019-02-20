package oktenweb.school.models.functional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oktenweb.school.models.custom.Classteachers;
import oktenweb.school.models.custom.Deputy;
import oktenweb.school.models.custom.Teachers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Subjects {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Classes> classes = new ArrayList<>();


    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Teachers teachers;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Deputy deputy;


    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "subjects"
    )
    private List<ClassJournal> classJournal = new ArrayList<>();



    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Classteachers classteachers;

    public Subjects() {
    }

    public Subjects(String subject) {
        this.name = subject;
    }


    public Classteachers getClassteachers() {
        return classteachers;
    }

    public void setClassteachers(Classteachers classteachers) {
        this.classteachers = classteachers;
    }

    public List<ClassJournal> getClassJournal() {
        return classJournal;
    }

    public void setClassJournal(List<ClassJournal> classJournal) {
        this.classJournal = classJournal;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                ", teachers=" + teachers +
                ", deputy=" + deputy +
                ", classJournal=" + classJournal +
                ", classteachers=" + classteachers +
                '}';
    }
}


