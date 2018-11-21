package oktenweb.school.controllers;


import oktenweb.school.dao.UserDAO;
import oktenweb.school.models.Role;
import oktenweb.school.models.User;
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
    UserDAO userDAO;

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


    @PostMapping("/saveUser")
    public String saveUser(User user) {
        System.out.println("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(Role.ROLE_TEACHER);
//        user.setRole(Role.ROLE_ADMIN);
//        user.setRole(Role.ROLE_CLASSTHEACHER);
//        user.setRole(Role.ROLE_DEPUTI);
//        user.setRole(Role.ROLE_PARENT);
//        user.setRole(Role.ROLE_STUDENT);
        userDAO.save(user);
        return "redirect:/";
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
    @GetMapping("/account")
    private String account()
    {
        return "account";
    }
    @GetMapping("/chat")
    private String chat()
    {
        return "chat";
    }
    @GetMapping("/homework")
    private String homework()
    {
        return "homework";
    }
    @GetMapping("/lesson")
    private String lesson()
    {
        return "lesson";
    }

    @GetMapping("/visited")
    private String visited()
    {
        return "visited";
    }

    @GetMapping("/allhref")
    private String allhref()
    {
        return "hhh";
    }


}
