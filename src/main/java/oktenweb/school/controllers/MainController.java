package oktenweb.school.controllers;


import oktenweb.school.dao.UserDAO;
import oktenweb.school.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {


    @Autowired
    UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(){
        return "login";
    }


    @PostMapping("/successURL")
    public String successURL(){
        System.out.println("You succes login");
        return "succesed";
    }

    @PostMapping("/lessonURL")
    public String lessonURL(){
        return "lesson";


    }    @PostMapping("/marksURL")
    public String marksURL(){
        return "marks";


    }    @PostMapping("/newsURL")
    public String newsURL(){
        return "news";
    }


    @PostMapping("/saveUser")
    public String saveUser(User user){
        System.out.println("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole();
        userDAO.save(user);
        return "redirect:/";
    }



}
