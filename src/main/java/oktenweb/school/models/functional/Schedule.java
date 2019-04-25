package oktenweb.school.models.functional;

import oktenweb.school.models.Days;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int subject_number;
    private String subject_name;
    private String class_name;

    public Schedule(int subject_number, String subject_name, String class_name) {
        this.subject_number = subject_number;
        this.subject_name = subject_name;
        this.class_name = class_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_number() {
        return subject_number;
    }

    public void setSubject_number(int subject_number) {
        this.subject_number = subject_number;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    @Enumerated(EnumType.STRING)
    private Days days;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", subject_number=" + subject_number +
                ", subject_name='" + subject_name + '\'' +
                ", class_name='" + class_name + '\'' +
                ", days=" + days +
                '}';
    }
}
