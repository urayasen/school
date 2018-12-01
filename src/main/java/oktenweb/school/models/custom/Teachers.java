package oktenweb.school.models.custom;

import oktenweb.school.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Teachers  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Name;
    private String Surname;
    private String Email;
    private String Phone;
    private String Adress;
    private String gender;
    private String birthday;



    @Autowired
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name = "user_id")
    private User user;

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
        return Name;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Adress='" + Adress + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", user=" + user +
                '}';
    }
}
