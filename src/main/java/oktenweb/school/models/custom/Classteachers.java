package oktenweb.school.models.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oktenweb.school.models.User;
import oktenweb.school.models.functional.ClassJournal;
import oktenweb.school.models.functional.Classes;
import oktenweb.school.models.functional.Subjects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classteachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String adress;
    private String gender;
    private String birthday;
    transient private String loginname;


    public String getLoginname() {
        return user.getUsername();
    }

    public void setLoginname() {
        this.loginname = user.getUsername();
    }

    //fgsgfgg
    @JsonIgnore
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classteachers"
    )
    private List<Subjects> subjects = new ArrayList<>();


    @JsonIgnore
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classteachers"
    )
    private Classes classes;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classteachers"
    )
    private List<Students> students = new ArrayList<>();



//    @JsonIgnore
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "classteachers"
//    )
//    private List<ClassJournal> classJournals = new ArrayList<>();



    @JsonIgnore
    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

//    public List<ClassJournal> getClassJournals() {
//        return classJournals;
//    }

//    public void setClassJournals(List<ClassJournal> classJournals) {
//        this.classJournals = classJournals;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Classteachers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", loginname='" + loginname + '\'' +
                '}';
    }
}
