package oktenweb.school.models.custom;

import oktenweb.school.models.User;
import oktenweb.school.models.functional.ClassJournal;
import oktenweb.school.models.functional.Classes;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Students {
//ghxghxfghgh

    /*--------------------------------------------------------------------------------------------------*/
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Autowired
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name = "user_id")
    private User user;


    @Autowired
    @OneToMany( fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "students"
    )
    private List<ClassJournal> classJournals = new ArrayList<>();

    @Autowired
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Parents parents;

    @Autowired
    @OneToMany( fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "students"
    )
    private List<Classes> classes = new ArrayList<>();

    @Autowired
    @ManyToOne( fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    private Classteachers classteachers;

    public Classteachers getClassteachers() {
        return classteachers;
    }

    public void setClassteachers(Classteachers classteachers) {
        this.classteachers = classteachers;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public List<ClassJournal> getClassJournals() {
        return classJournals;
    }

    public void setClassJournals(List<ClassJournal> classJournals) {
        this.classJournals = classJournals;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    /*--------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", user=" + user +
                ", classJournals=" + classJournals +
                ", parents=" + parents +
                ", classes=" + classes +
                ", classteachers=" + classteachers +
                '}';
    }
}
