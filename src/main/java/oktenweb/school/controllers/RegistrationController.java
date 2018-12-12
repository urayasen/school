package oktenweb.school.controllers;

import oktenweb.school.models.Role;
import oktenweb.school.models.functional.Subjects;
import oktenweb.school.models.User;
import oktenweb.school.models.custom.*;
import oktenweb.school.models.functional.ListSubjects;
import oktenweb.school.service.UserService;
import oktenweb.school.service.customService.*;
import oktenweb.school.service.functionalService.SubjectsService;
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
import java.util.Iterator;
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

    @Autowired
    SubjectsService subjectsService;


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

        @GetMapping("/registrationFunctional")
        public String registrationFunctional(Model model){

            ListSubjects listSubjects = new ListSubjects();
            listSubjects.addSubjects();
            List<String> listSubjectres = listSubjects.getListSubjectres();
            Iterator<String> iterator = listSubjectres.iterator();
            Map<String, String> mapSubjects = new HashMap<>();
            while (iterator.hasNext()) {
                String subject = iterator.next();
                mapSubjects.put(subject, subject);
            }
            model.addAttribute("mapSubjects", mapSubjects);
            return "/functional/registrationFunctional";
        }

        @GetMapping("/saveFunctional")
        public String saveFunctional(
              Subjects subjects) {
            String name = subjects.getName();
            System.out.println(name);
            String[] split = name.split(",");
            for (int i=0; i<split.length; i++) {
                String sSubject = split[i];

               Subjects subjects1 = new Subjects(sSubject);
                subjectsService.save(subjects1);
            }
//            subjectsService.save(subjects);
             return"redirect:/";
        }



}
