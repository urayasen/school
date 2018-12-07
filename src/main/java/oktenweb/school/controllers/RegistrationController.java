package oktenweb.school.controllers;

import oktenweb.school.models.Role;
import oktenweb.school.models.Subjects;
import oktenweb.school.models.User;
import oktenweb.school.models.custom.*;
import oktenweb.school.service.UserService;
import oktenweb.school.service.customService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DeputyService deputyService;

    @Autowired
    ClassteachersService classteachersService;

    @Autowired
    ParentsService parentsService;

    @Autowired
    StudentsService studentsService;

    @Autowired
    TeachersService teachersService;


    @PostMapping("/saveUser")
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
            return "registationClassteacher";
        }else if(user1.getRole()==Role.ROLE_DEPUTI){
            return "registrationDeputi";
        }else if(user1.getRole()==Role.ROLE_PARENT){
            return "registrationParent";
        }else if(user1.getRole()==Role.ROLE_TEACHER){
            return "registrationTeacher";
        }

        return  null;
    }



    @GetMapping("/saveNewUser")
    public String saveNewUser(Model model) {
        Map<String, Role> mapRoles = new HashMap<>();
        mapRoles.put("Студент", Role.ROLE_STUDENT);
//        mapRoles.put("Адміністратор", Role.ROLE_ADMIN);
        mapRoles.put("Вчитель", Role.ROLE_TEACHER);
        mapRoles.put("Батько", Role.ROLE_PARENT);
        mapRoles.put("Класний керівник", Role.ROLE_CLASSTHEACHER);
        mapRoles.put("Зауч", Role.ROLE_DEPUTI);
        model.addAttribute("mapRoles", mapRoles);
        return "registration";
    }

    @GetMapping("/saveDeputy")
    public String saveDeputy(Deputy deputy, int id) {
        System.out.println("saveDeputy ---------   " + id);
        User user = userService.byId(id);
        deputy.setUser(user);
        deputyService.save(deputy);
        return "redirect:/";
    }

    @GetMapping("/saveTeachers")
    public String saveTeachers(Teachers teachers, int id) {
        System.out.println("saveTeachers ---------   " + id);
        User user = userService.byId(id);
        teachers.setUser(user);
        teachersService.save(teachers);
        return "redirect:/";
    }

    @GetMapping("/saveParents")
    public String saveParents(Parents parents, int id) {
        System.out.println("saveParents ---------   " + id);
        User user = userService.byId(id);
        parents.setUser(user);
        parentsService.save(parents);
        return "redirect:/";
    }

        @GetMapping("/saveClassteachers" )
        public String saveClassteachers(Classteachers classteachers, int id){
            System.out.println("saveClassteachers ---------   " + id);
            User user = userService.byId(id);
            classteachers.setUser(user);
            classteachersService.save(classteachers);
            return "redirect:/";
        }

        @PostMapping ("/saveStudents")
        public String registrationStudents(Students students, int id) {
            System.out.println("saveStudents ---------   " + id);
                User user = userService.byId(id);
                students.setUser(user);
                studentsService.save(students);
                return "redirect:/";
            }

        @GetMapping("/saveFunctional")
        public String registrationFunctional(Model model){
            Map<String, Subjects> mapSubjects = new HashMap<>();
            mapSubjects.put("Математика", Subjects.MATH);
//        mapRoles.put("Адміністратор", Role.ROLE_ADMIN);
            mapSubjects.put("Українська мова ", Subjects.UKR_LANG);
            mapSubjects.put("Біологія", Subjects.BIOLOGY);
            mapSubjects.put("Інформатика", Subjects.COM_SCIENCE);
            mapSubjects.put("Англійська мова ", Subjects.ENG_LANG);
            mapSubjects.put("Фізична культура", Subjects.PHYSICAL_EDUCATION);
            mapSubjects.put("Англійська мова ", Subjects.UKR_LIT);
            model.addAttribute("mapSubjects", mapSubjects);
            return "/functional/registrationFunctional";
        }



        }
