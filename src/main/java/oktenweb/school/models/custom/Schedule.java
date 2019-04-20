package oktenweb.school.models.custom;

import oktenweb.school.models.Days;

import javax.persistence.*;

@Entity
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject_name;
    private int class_teacher;

    private int class_name;


    public Schedule(String subject_name, int class_teacher,  int class_name) {
        this.subject_name = subject_name;
        this.class_teacher = class_teacher;
              this.class_name = class_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getClass_teacher() {
        return class_teacher;
    }

    public void setClass_teacher(int class_teacher) {
        this.class_teacher = class_teacher;
    }


    public int getClass_name() {
        return class_name;
    }

    public void setClass_name(int class_name) {
        this.class_name = class_name;
    }

    @Enumerated(EnumType.STRING)
    private Days days;

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", subject_name='" + subject_name + '\'' +
                ", class_teacher=" + class_teacher +
                ", class_name=" + class_name +
                ", days=" + days +
                '}';
    }
}



