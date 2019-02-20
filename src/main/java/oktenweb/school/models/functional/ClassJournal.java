package oktenweb.school.models.functional;

import oktenweb.school.models.custom.Classteachers;
import oktenweb.school.models.custom.Deputy;
import oktenweb.school.models.custom.Students;
import oktenweb.school.models.custom.Teachers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class ClassJournal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dateMark;
    private String marks;



    @Autowired
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Subjects subjects;


    @Autowired
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Students students;


    @Autowired
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Deputy deputy;


    @Autowired
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Classteachers classteachers;

    @Autowired
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Teachers teachers;


    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public Classteachers getClassteachers() {
        return classteachers;
    }

    public void setClassteachers(Classteachers classteachers) {
        this.classteachers = classteachers;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }


    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateMark() {
        return dateMark;
    }

    public void setDateMark(String dateMark) {
        this.dateMark = dateMark;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ClassJournal{" +
                "id=" + id +
                ", dateMark='" + dateMark + '\'' +
                ", marks='" + marks + '\'' +
                ", subjects=" + subjects +
                ", students=" + students +
                ", deputy=" + deputy +
                ", classteachers=" + classteachers +
                ", teachers=" + teachers +
                '}';
    }
}