package oktenweb.school.models.functional;


import oktenweb.school.models.custom.Classteachers;
import oktenweb.school.models.custom.Students;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Classes() {
    }

    public Classes(Integer id) {
        this.id = id;
    }

    public Classes(String name) {
        this.name = name;
    }

    public Classes(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Autowired
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classes"
    )
    private List<Subjects> subjects = new ArrayList<>();



    @Autowired
    @OneToMany (
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Students> students = new ArrayList<>();


    @Autowired
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Classteachers classteachers;


    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public Classteachers getClassteachers() {
        return classteachers;
    }

    public void setClassteachers(Classteachers classteachers) {
        this.classteachers = classteachers;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
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
        return "Classes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                ", students=" + students +
                ", classteachers=" + classteachers +
                '}';
    }
}
