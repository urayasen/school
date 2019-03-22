package oktenweb.school.models.custom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject_name;
    private int class_teacher;
    private String day_week;
    private int class_name;


    public Schedule(String subject_name, int class_teacher, String day_week, int class_name) {
        this.subject_name = subject_name;
        this.class_teacher = class_teacher;
        this.day_week = day_week;
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

    public String getDay_week() {
        return day_week;
    }

    public void setDay_week(String day_week) {
        this.day_week = day_week;
    }

    public int getClass_name() {
        return class_name;
    }

    public void setClass_name(int class_name) {
        this.class_name = class_name;
    }



    @Override
    public String toString() {
        return "ScheduleDAO{" +
                "id=" + id +
                ", subject_name='" + subject_name + '\'' +
                ", class_teacher=" + class_teacher +
                ", day_week='" + day_week + '\'' +
                ", class_name=" + class_name +
                '}';
    }
}



