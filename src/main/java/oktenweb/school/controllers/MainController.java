package oktenweb.school.controllers;


import oktenweb.school.dao.UserDAO;
import oktenweb.school.models.Role;
import oktenweb.school.models.User;
import oktenweb.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {



    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "login";
    }


    @PostMapping("/successURL")
    public String successURL() {
        System.out.println("You succes login");
        return "succesed";
    }


    @GetMapping("/saveUser")
    public String saveUser(User user, Model model) {
        System.out.println("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        int id = user.getId();
        userService.save(user);
        User user1 = userService.byId(user.getId());
        int id = user1.getId();
        System.out.println("saveUser ---------   " + id);
        model.addAttribute("user1", user1);
        if(user1.getRole()==Role.ROLE_STUDENT){
            return "registrationStudents";
        }else if(user1.getRole()==Role.ROLE_CLASSTHEACHER){
            return "registrationClassteacher";
        }else if(user1.getRole()==Role.ROLE_DEPUTI){
            return "registrationDeputi";
        }else if(user1.getRole()==Role.ROLE_PARENT){
            return "registrationParents";
        }else if(user1.getRole()==Role.){
            return "registrationTeacher";
        }
    }



    @GetMapping("/saveNewUser")
    public String saveNewUser(Model model) {
        Map<String, Role> mapRoles = new HashMap<>();
        mapRoles.put("Студент", Role.ROLE_STUDENT);
        mapRoles.put("Адміністратор", Role.ROLE_ADMIN);
        mapRoles.put("Вчитель", Role.ROLE_TEACHER);
        mapRoles.put("Батько", Role.ROLE_PARENT);
        mapRoles.put("Класний керівник", Role.ROLE_CLASSTHEACHER);
        mapRoles.put("Зауч", Role.ROLE_DEPUTI);
        model.addAttribute("mapRoles", mapRoles);
        return "registration";
    }

    @GetMapping("/admin/news")
    private String news()
    {
        return "news";
    }

    @GetMapping("/marks")
    private String marks()
    {
        return "marks";
    }
}
