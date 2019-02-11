package oktenweb.school.controllers;

import oktenweb.school.models.Role;
import oktenweb.school.models.functional.Classes;
import oktenweb.school.models.functional.Subjects;
import oktenweb.school.models.User;
import oktenweb.school.models.custom.*;
import oktenweb.school.models.functional.ListSubjects;
import oktenweb.school.service.ParentService;
import oktenweb.school.service.UserService;
import oktenweb.school.service.customService.*;
import oktenweb.school.service.functionalService.ClassesService;
import oktenweb.school.service.functionalService.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RegistrationController {
    //123
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

    @Autowired
    ClassesService classesService;


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
        if (user1.getRole() == Role.ROLE_STUDENT) {
            return "registrationStudents";
        } else if (user1.getRole() == Role.ROLE_CLASSTHEACHER) {
            return "registationClassteacher";
        } else if (user1.getRole() == Role.ROLE_DEPUTI) {
            return "registrationDeputi";
        } else if (user1.getRole() == Role.ROLE_PARENT) {
            return "registrationParent";
        } else if (user1.getRole() == Role.ROLE_TEACHER) {
            return "registrationTeacher";
        }

        return null;
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

    @GetMapping("/saveClassteachers")
    public String saveClassteachers(Classteachers classteachers, int id) {
        System.out.println("saveClassteachers ---------   " + id);
        User user = userService.byId(id);
        classteachers.setUser(user);
        classteachersService.save(classteachers);
        return "redirect:/";
    }

    @PostMapping("/saveStudents")
    public String registrationStudents(Students students, int id) {
        System.out.println("saveStudents ---------   " + id);
        User user = userService.byId(id);
        students.setUser(user);
        studentsService.save(students);
        return "redirect:/";
    }

    @GetMapping("/registrationFunctional")
    public String registrationFunctional(Model model) {

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
    public String saveFunctional(Subjects subjects) {
        System.out.println(subjects.toString());
        String[] listSubjects = subjects.getName().split(",");
        for (String subject : listSubjects) {
            Subjects newSubjects = new Subjects(subject);
            subjectsService.save(newSubjects);
        }

        return "redirect:/";
    }

    @GetMapping("/registrationClasses")
    public String registrationClasses(
            Model model) {
        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes", classes);
        return "registrationClasses";
    }

    @GetMapping("/saveClasses")
    public String saveClasses(@RequestParam Integer id, @RequestParam String name) {

        Classes classes;
        if (id == null) {
            classes = new Classes(name);
        } else {
            classes = new Classes(id, name);
        }
        classesService.save(classes);

        return "redirect:/registrationClasses";
    }


    @GetMapping("/edit/{id}")
    public String resolveSingleContact(@PathVariable/*("id")*/ int id,
                                       Model model) {
        Classes classes = classesService.byId(id);
        model.addAttribute("classes", classes);
//        System.out.println(contact);
//        System.out.println(model);
        return "registrationClasses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        classesService.deleteById(id);
        return "redirect:/registrationClasses";
    }

    private Map<String, String> elements;

    @GetMapping("/regAddElements/all")
    public String regAddElementsinAll(Model model) {
        elements = new HashMap<>();
        elements.put("Класний керівник", "classteachers");
        elements.put("Класи", "classes");
        elements.put("Зауч", "deputy");
        elements.put("Батьки", "parents");
        elements.put("Студенти", "students");
        elements.put("Викладачі", "teachers");
//        elements.put("Класний журнал", "class_journal");
        elements.put("Предмети", "subjects");
        model.addAttribute("elements", elements);
        return "registrationAddElements";
    }

    //вибір елементів
    @GetMapping("/regAddElements/{name}")
    public @ResponseBody
    List<Object> regAddElementsByName(@PathVariable String name) {
        List<Object> parentElements = new ArrayList<>();
        if (name.equals("classteachers")) {
            System.out.println("classteachers");
            List<Classteachers> classteachers = classteachersService.findAll();
            Iterator<Classteachers> iterator = classteachers.iterator();
            while (iterator.hasNext()) {
                Classteachers oneClassteacher = iterator.next();
                parentElements.add(oneClassteacher);
            }


        } else if (name.equals("teachers")) {
            System.out.println("teachers");
            List<Teachers> teachers = teachersService.findAll();
            Iterator<Teachers> iterator = teachers.iterator();
            while (iterator.hasNext()) {
                Teachers oneTeacher = iterator.next();
                ParentService oneParentElement = oneTeacher;
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("classes")) {
            System.out.println("classes");
            List<Classes> classes = classesService.findAll();
            Iterator<Classes> iterator = classes.iterator();
            while (iterator.hasNext()) {
                Classes oneClass = iterator.next();
                ParentService oneParentElement = oneClass;
                parentElements.add(oneParentElement);
            }

        } else if (name.equals("deputy")) {
            System.out.println("deputy");
            List<Deputy> deputy = deputyService.findAll();
            Iterator<Deputy> iterator = deputy.iterator();
            while (iterator.hasNext()) {
                Deputy oneDeputy = iterator.next();
                ParentService oneParentElement = oneDeputy;
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("parents")) {
            System.out.println("parents");
            List<Parents> parents = parentsService.findAll();
            Iterator<Parents> iterator = parents.iterator();
            while (iterator.hasNext()) {
                Parents oneParent = iterator.next();
                ParentService oneParentElement = oneParent;
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("students")) {
            System.out.println("students");
            List<Students> students = studentsService.findAll();
            Iterator<Students> iterator = students.iterator();
            while (iterator.hasNext()) {
                Students oneStudent = iterator.next();
                ParentService oneParentElement = oneStudent;
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("subjects")) {
            System.out.println("subjects");
            List<Subjects> subjects = subjectsService.findAll();
            Iterator<Subjects> iterator = subjects.iterator();
            while (iterator.hasNext()) {
                Subjects oneSubject = iterator.next();
                ParentService oneParentElement = oneSubject;
                parentElements.add(oneParentElement);
            }
        }

        return parentElements;

    }

    @GetMapping("/saveElements")
    public void saveElements(@RequestBody String body) {
        System.out.println(body);


    }


}
