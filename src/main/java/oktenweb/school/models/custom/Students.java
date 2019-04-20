package oktenweb.school.models.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oktenweb.school.models.User;
import oktenweb.school.models.functional.ClassJournal;
import oktenweb.school.models.functional.Classes;

import javax.persistence.*;
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
    private String avatar;
    transient private String loginname;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLoginname() {
        return user.getUsername();
    }

    public void setLoginname() {
        this.loginname = user.getUsername();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonIgnore
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @OneToMany( fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "students"
    )
    private List<ClassJournal> classJournals = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Parents parents;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    private Classes classes ;

    @JsonIgnore
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
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
                ", avatar='" + avatar + '\'' +
                ", loginname='" + loginname + '\'' +
                '}';
    }
}
