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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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


    @PostMapping("/saveUser")
    public String saveUser(User user){
        System.out.println("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole();
        userDAO.save(user);
        return "redirect:/";
    }

    @GetMapping("/saveNewUser")
    public String saveNewUser(Model model){
        Map<String, Role> mapRoles = new HashMap<>();
        mapRoles.put("Студент", Role.ROLE_STUDENT);
        mapRoles.put("Адміністратор", Role.ROLE_ADMIN);
        mapRoles.put("Вчитель", Role.ROLE_TEACHER);
        mapRoles.put("Батько", Role.ROLE_PARENT);
        mapRoles.put("Класний керівник", Role.ROLE_CLASSTHEACHER);
        mapRoles.put("Зауч", Role.ROLE_DEPUTI);

        model.addAttribute("mapRoles",mapRoles);
        return "registration";
    }

    @GetMapping("/news")
    public String news(){
        return "news";
    }
}
