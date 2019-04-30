package oktenweb.school.controllers;

import oktenweb.school.models.Days;
import oktenweb.school.models.Role;
import oktenweb.school.models.functional.Classes;
import oktenweb.school.models.functional.Schedule;
import oktenweb.school.models.functional.Subjects;
import oktenweb.school.models.User;
import oktenweb.school.models.custom.*;
import oktenweb.school.models.functional.ListSubjects;
import oktenweb.school.service.UserService;
import oktenweb.school.service.customService.*;
import oktenweb.school.service.functionalService.ClassJournalService;
import oktenweb.school.service.functionalService.ClassesService;
import oktenweb.school.service.functionalService.ScheduleService;
import oktenweb.school.service.functionalService.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    ClassJournalService classJournalService;

    @Autowired
    ScheduleService scheduleService;


    @PostMapping("/saveUser")
    public String saveUser(User user, Model model) {
        System.out.println("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        int id = user.getId();
        System.out.println(user);
        userService.save(user);
        User user1 = userService.byId(user.getId());
        int id = user1.getId();
        System.out.println("saveUser ---------   " + id);
        model.addAttribute("user1", user1);
        if (user1.getRole() == Role.ROLE_STUDENT) {
            return "registrationUser/registrationStudents";
        } else if (user1.getRole() == Role.ROLE_CLASSTEACHER) {
            return "registrationUser/registationClassteacher";
        } else if (user1.getRole() == Role.ROLE_DEPUTI) {
            return "registrationUser/registrationDeputi";
        } else if (user1.getRole() == Role.ROLE_PARENT) {
            return "registrationUser/registrationParent";
        } else if (user1.getRole() == Role.ROLE_TEACHER) {
            return "registrationUser/registrationTeacher";
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
        mapRoles.put("Класний керівник", Role.ROLE_CLASSTEACHER);
        mapRoles.put("Зауч", Role.ROLE_DEPUTI);
        model.addAttribute("mapRoles", mapRoles);
        return "main/registration";
    }

    @GetMapping("/saveDeputy")
    public String saveDeputy(Deputy deputy, int id) {
        System.out.println("saveDeputy ---------   " + id);
        User user = userService.byId(id);
        deputy.setUser(user);
        deputyService.save(deputy);
        return "redirect:/login";
    }

    @GetMapping("/saveTeachers")
    public String saveTeachers(Teachers teachers, int id) {
        System.out.println("saveTeachers ---------   " + id);
        User user = userService.byId(id);
        teachers.setUser(user);
        teachersService.save(teachers);
        return "redirect:/login";
    }

    @GetMapping("/saveParents")
    public String saveParents(Parents parents, int id) {
        System.out.println("saveParents ---------   " + id);
        User user = userService.byId(id);
        parents.setUser(user);
        parentsService.save(parents);
        return "redirect:/login";
    }

    @GetMapping("/saveClassteachers")
    public String saveClassteachers(Classteachers classteachers, int id) {
        System.out.println("saveClassteachers ---------   " + id);
        User user = userService.byId(id);
        classteachers.setUser(user);
        classteachersService.save(classteachers);
        return "redirect:/login";
    }

    @PostMapping("/saveStudents")
    public String registrationStudents(Students students, int id, @RequestParam MultipartFile image) throws IOException {
        System.out.println("saveStudents ---------   " + id);
        User user = userService.byId(id);

        students.setUser(user);
        String path = System.getProperty("user.home")
                + File.separator
                + "images"
                + File.separator
                + image.getOriginalFilename();

        image.transferTo(new File(path));
        students.setAvatar(image.getOriginalFilename());
        studentsService.save(students);
        return "redirect:/login";
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
        return "/registrationFunction/registrationFunctional";
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
        return "registrationFunction/registrationClasses";
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
        return "registrationFunction/registrationClasses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        classesService.deleteById(id);
        return "redirect:registrationFunction/registrationClasses";
    }

    private Map<String, String> elements;

    @GetMapping("/regAddElements/all")
    public String regAddElementsinAll(Model model) {
        elements = new HashMap<>();
        elements.put("Класний керівник", "classteachers");
        elements.put("Класи", "classes");
        elements.put("Зауч", "deputy");
        elements.put("Батьки", "parents");
//        elements.put("Студенти", "students");
        elements.put("Викладачі", "teachers");
//        elements.put("Класний журнал", "class_journal");
        elements.put("Предмети", "subjects");
        model.addAttribute("elements", elements);
        return "registrationFunction/registrationAddElements";
    }

    //вибір елементів
    @GetMapping("/regAddElements/{name}")
    public @ResponseBody
    List<Object> regAddElementsByName(@PathVariable String name) {
        List<Object> parentElements = new ArrayList<>();
        if (name.equals("classteachers")) {

            List<Classteachers> classteachers = classteachersService.findAll();
            Iterator<Classteachers> iterator = classteachers.iterator();
            while (iterator.hasNext()) {
                Classteachers oneClassteacher = iterator.next();
                parentElements.add(oneClassteacher);
            }


        } else if (name.equals("teachers")) {

            List<Teachers> teachers = teachersService.findAll();
            Iterator<Teachers> iterator = teachers.iterator();
            while (iterator.hasNext()) {
                Teachers oneParentElement = iterator.next();
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("classes")) {

            List<Classes> classes = classesService.findAll();
            Iterator<Classes> iterator = classes.iterator();
            while (iterator.hasNext()) {
                Classes oneParentElement = iterator.next();
                parentElements.add(oneParentElement);
            }

        } else if (name.equals("deputy")) {

            List<Deputy> deputy = deputyService.findAll();
            Iterator<Deputy> iterator = deputy.iterator();
            while (iterator.hasNext()) {
                Deputy oneParentElement = iterator.next();
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("parents")) {

            List<Parents> parents = parentsService.findAll();
            Iterator<Parents> iterator = parents.iterator();
            while (iterator.hasNext()) {
                Parents oneParentElement = iterator.next();
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("students")) {

            List<Students> students = studentsService.findAll();
            Iterator<Students> iterator = students.iterator();
            while (iterator.hasNext()) {
                Students oneParentElement = iterator.next();
                parentElements.add(oneParentElement);
            }
        } else if (name.equals("subjects")) {

            List<Subjects> subjects = subjectsService.findAll();
            Iterator<Subjects> iterator = subjects.iterator();
            while (iterator.hasNext()) {
                Subjects oneParentElement = iterator.next();
                parentElements.add(oneParentElement);
            }
        }

        return parentElements;

    }

    @PostMapping("/saveElements")
    public @ResponseBody
    String saveElements(@RequestBody String arrElements) {
        System.out.println(arrElements);

        String arrElementsNew = arrElements.replaceAll("&", " ");
        System.out.println(arrElementsNew);
        String stringElementsNew = arrElementsNew.replaceAll("%5B%5D", "");
        System.out.println(stringElementsNew);
        String[] arrayStringElements = stringElementsNew.split(" ");
//        if (arrayStringElements[0].equals("elementsOne=parents") || (arrayStringElements[1].equals("elementsTwo=students"))) {


        List<String> arraySingleElements = new ArrayList<>();
        String s;
        for (int i = 2; i < arrayStringElements.length; i++) {
            if (arrayStringElements[i].contains("stringlistElementsOne=")) {
                s = arrayStringElements[i].replaceAll("stringlistElementsOne=", "");
                if (!s.equals("")) {
                    arraySingleElements.add(s);
                }
            } else {
                s = arrayStringElements[i].replaceAll("stringlistElementsTwo=", "");
                if (!s.equals("")) {
                    arraySingleElements.add(s);
                }
            }
        }


        System.out.println(arraySingleElements);


        if (arrayStringElements[0].equals("elementsOne=parents") && (arrayStringElements[1].equals("elementsTwo=students"))) {
            Parents parents = null;
            Students students = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    parents = parentsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    List<Students> students1 = parents.getStudents();
                    Iterator<Students> iterator = students1.iterator();
                    while (iterator.hasNext()) {
                        students = iterator.next();
                        students.setParents(null);
                        studentsService.save(students);
                    }
                    System.out.println(parents);
                } else {
                    students = studentsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(students);
                    students.setParents(parents);
                    studentsService.save(students);
                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=classteachers") && (arrayStringElements[1].equals("elementsTwo=classes"))) {
            Classteachers classteachers = null;
            Classes classes = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    classteachers = classteachersService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(classteachers);
                    classes = classteachers.getClasses();
                    System.out.println(classes);
                    if (classes == null) continue;
                    classes.setClassteachers(null);
                    classesService.save(classes);
                } else {
                    classes = classesService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    classes.setClassteachers(classteachers);
                    classesService.save(classes);

                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=classteachers") && (arrayStringElements[1].equals("elementsTwo=students"))) {
            Classteachers classteachers = null;
            Students students = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    classteachers = classteachersService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(classteachers);
                    List<Students> students1 = classteachers.getStudents();
                    System.out.println(students1);
                    Iterator<Students> iterator = students1.iterator();
                    while (iterator.hasNext()) {
                        students = iterator.next();

                        System.out.println(students);
                        students.setClassteachers(null);
                        studentsService.save(students);
                    }

                    System.out.println(classteachers);
                } else {
                    students = studentsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(students);
                    students.setClassteachers(classteachers);
                    studentsService.save(students);
                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=classteachers") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
            Classteachers classteachers = null;
            Subjects subjects = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    classteachers = classteachersService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(classteachers);
                    List<Subjects> subjects1 = classteachers.getSubjects();
                    System.out.println(subjects1);
                    Iterator<Subjects> iterator = subjects1.iterator();
                    while (iterator.hasNext()) {
                        subjects = iterator.next();

                        System.out.println(subjects);
                        subjects.setClassteachers(null);
                        subjectsService.save(subjects);
                    }

                    System.out.println(classteachers);
                } else {
                    subjects = subjectsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(subjects);
                    subjects.setClassteachers(classteachers);
                    subjectsService.save(subjects);
                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=deputy") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
            Deputy deputy = null;
            Subjects subjects = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    deputy = deputyService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(deputy);
                    List<Subjects> subjects1 = deputy.getSubjects();
                    System.out.println(subjects1);
                    Iterator<Subjects> iterator = subjects1.iterator();
                    while (iterator.hasNext()) {
                        subjects = iterator.next();

                        System.out.println(subjects);
                        subjects.setDeputy(null);
                        subjectsService.save(subjects);
                    }

                    System.out.println(deputy);
                } else {
                    subjects = subjectsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(subjects);
                    subjects.setDeputy(deputy);
                    subjectsService.save(subjects);
                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=teachers") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
            Teachers teachers = null;
            Subjects subjects = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    teachers = teachersService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(teachers);
                    List<Subjects> subjects1 = teachers.getSubjects();
                    System.out.println(subjects1);
                    Iterator<Subjects> iterator = subjects1.iterator();
                    while (iterator.hasNext()) {
                        subjects = iterator.next();

                        System.out.println(subjects);
                        subjects.setTeachers(null);
                        subjectsService.save(subjects);
                    }

                    System.out.println(teachers);
                } else {
                    subjects = subjectsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(subjects);
                    subjects.setTeachers(teachers);
                    subjectsService.save(subjects);
                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=classes") && (arrayStringElements[1].equals("elementsTwo=students"))) {
            Classes classes = null;
            Students students = null;

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    classes = classesService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(classes);
                    List<Students> students1 = classes.getStudents();
                    System.out.println(students1);
                    Iterator<Students> iterator = students1.iterator();
                    while (iterator.hasNext()) {
                        students = iterator.next();

                        System.out.println(students);
                        students.setClasses(null);
                        studentsService.save(students);
                    }

                    System.out.println(students);
                } else {
                    students = studentsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(students);
                    students.setClasses(classes);
                    studentsService.save(students);
                }
            }

        } else if (arrayStringElements[0].equals("elementsOne=classes") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
            Classes classes = null;
            Subjects subjects = null;
            List<Subjects> subjects1 = new ArrayList<>();

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    classes = classesService.byId(Integer.parseInt(arraySingleElements.get(i)));

                } else {
                    subjects = subjectsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(subjects);
                    subjects1.add(subjects);
//
                }
            }

            classes.setSubjects(subjects1);
            classesService.save(classes);


        } else if (arrayStringElements[0].equals("elementsOne=subjects") && (arrayStringElements[1].equals("elementsTwo=classes"))) {
            Classes classes = null;
            Subjects subjects = null;
            List<Classes> classes1 = new ArrayList<>();

            for (int i = 0; i < arraySingleElements.size(); i++) {
                if (i == 0) {
                    subjects = subjectsService.byId(Integer.parseInt(arraySingleElements.get(i)));
                } else {
                    classes = classesService.byId(Integer.parseInt(arraySingleElements.get(i)));
                    System.out.println(classes);
                    classes1.add(classes);

                }
            }

            subjects.setClasses(classes1);
            subjectsService.save(subjects);

        }


        return "111";
    }

////111111
    @GetMapping("/SelectElements")
    public String selectSubjects(Model model) {
        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes", classes);

        List<Subjects> subjects = subjectsService.findAll();
        model.addAttribute("subjects", subjects);

        List<String> months = new ArrayList<>();
        months.add("Січень");
        months.add("Лютий");
        months.add("Березень");
        months.add("Квітень");
        months.add("Травень");
        months.add("Червень");
        months.add("Липень");
        months.add("Серпень");
        months.add("Вересень");
        months.add("Жовтень");
        months.add("Листопад");
        months.add("Грудень");

        model.addAttribute("months", months);
        return "functional/marks";


    }

    @GetMapping("/buttonSelectElements")
    public String btnSelectElements(@RequestParam("classes") int classes_id,
                                    @RequestParam("subjects") int subject_id,
                                    @RequestParam("months") String month_name,
                                    Model model) {

        System.out.println("classes_id = " + classes_id);
        System.out.println("subject_id = " + subject_id);
        System.out.println("month_name = " + month_name);


        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes", classes);

        List<Subjects> subjects = subjectsService.findAll();
        model.addAttribute("subjects", subjects);

        List<String> months = new ArrayList<>();
        months.add("Січень");
        months.add("Лютий");
        months.add("Березень");
        months.add("Квітень");
        months.add("Травень");
        months.add("Червень");
        months.add("Липень");
        months.add("Серпень");
        months.add("Вересень");
        months.add("Жовтень");
        months.add("Листопад");
        months.add("Грудень");

        model.addAttribute("months", months);


        model.addAttribute("classed", classesService.byId(classes_id).getName());
        model.addAttribute("subjected", subjectsService.byId(subject_id).getName());
        model.addAttribute("monthed", month_name);


        List<Students> studentsList = studentsService.byClassId(classes_id);

        model.addAttribute("students", studentsList);
        List<Integer> dates = new ArrayList<>();
        dates.add(2);
        dates.add(3);
        dates.add(5);
        dates.add(7);

        model.addAttribute("dates", dates);

        return "functional/marks";
    }

    @GetMapping("/buttonSaveMarks")
    public String buttonSaveMarks(@RequestParam("subjected") String subject,
                                  @RequestParam("dates") String dates,
                                  @RequestParam("marks") String marks,
                                  @RequestParam("students") String students,
                                  Model model) {


        return "marks";
    }


    @GetMapping("/schedule")
    public String schedule(Model model) {
        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes", classes);
        return "functional/schedule";
    }

    @GetMapping("/saveSchedule")
    public String saveSchedule(@RequestParam("classes") int classes_id,
                               Model model) {


        Days monday = Days.MONDAY;

        model.addAttribute("monday", monday);

        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes", classes);

        Classes classed = classesService.byId(classes_id);

        System.out.println(classed);

        List<Subjects> subjectsList = classed.getSubjects();

        model.addAttribute("subjects", subjectsList);



        return "functional/schedule";
    }

//    @PostMapping("/saveMonday")
//    public String saveMonday(Model model, Schedule schedule, Integer id) {
////        Map<String, Days> mapRoles = new HashMap<>();
////        mapRoles.put("Понеділок", Days.MONDAY);
////        mapRoles.put("Вівторок", Days.TUESDAY);
////        mapRoles.put("Середа", Days.WEDNESDAY);
////        mapRoles.put("Четвер", Days.THURSDAY);
////        mapRoles.put("П'ятниця", Days.FRIDAY);
//
//        if (schedule.getDays() == Days.MONDAY){
//
//            List<Subjects> subjects = subjectsService.findAll();
//
//            System.out.println(subjects);
//
//            Teachers teachers = teachersService.byId(id);
//
//            System.out.println(teachers);
//
//            scheduleService.save(schedule);        }
//        return "/functional/schedule";
//    }

    @GetMapping("/changeSubjects/{id}")
    public @ResponseBody
    Teachers changeSubjects(@PathVariable/*("id")*/ int id) {


        System.out.println("id=" + id);
        Subjects subjects = subjectsService.byId(id);
        System.out.println(subjects);

        Teachers teachers = subjects.getTeachers();
        System.out.println(teachers);
//        model.addAttribute("teacher", teachers.getName());
        return teachers;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Authentication authentication,
                          Model model,
                          Integer id,
                          User user,
                          Students students,
                          Teachers teachers,
                          Classteachers classteachers,
                          Parents parents,
                          Deputy deputy
    ) {
        String name = authentication.getName();
        String name1 = authentication.getName();
        String name2 = authentication.getName();
        String name3 = authentication.getName();
        String name4 = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(System.out::println);
        System.out.println(authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT")));

        if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_CLASSTEACHER"))) {
            User byUsername = userService.findByUsername(name2);
            Classteachers classteachers1 = byUsername.getClassteachers();
            String classes = classteachers1.getClasses().getName();
            String subjects = classteachers1.getSubjects().toString();

            model.addAttribute("classteachers1", classteachers1);
            model.addAttribute("classes", classes);
            model.addAttribute("subjects", subjects);

        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"))) {
            User byUsername = userService.findByUsername(name);
            Students students1 = byUsername.getStudents();
            String classes = students1.getClasses().getName();
            String parents_name = students1.getParents().getName();
            String parents_surname = students1.getParents().getSurname();
            String avatar1 = students1.getAvatar();

            model.addAttribute("students1", students1);
            model.addAttribute("classes", classes);
            model.addAttribute("parents_name", parents_name);
            model.addAttribute("parents_surname", parents_surname);
            model.addAttribute("avatar1", avatar1);

        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_TEACHER"))) {
            User byUsername = userService.findByUsername(name1);
            Teachers teachers1 = byUsername.getTeachers();
            List<Subjects> subjects = teachers1.getSubjects();

            model.addAttribute("teachers1", teachers1);
            model.addAttribute("subjects", subjects);

        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_PARENT"))) {
            User byUsername = userService.findByUsername(name3);
            Parents parents1 = byUsername.getParents();
            List<Students> students1 = parents1.getStudents();
            model.addAttribute("parents1", parents1);
            model.addAttribute("students1", students1);
        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_DEPUTI"))) {
            User byUsername = userService.findByUsername(name4);
            Deputy deputy1 = byUsername.getDeputy();
            List<Subjects> subjects = deputy1.getSubjects();
            String avatar1 = deputy1.getAvatar();

            model.addAttribute("deputy1", deputy1);
            model.addAttribute("subjects", subjects);
            model.addAttribute("avatar1", avatar1);
        }
        return "main/index";
    }


    @RequestMapping(value = "/savePhoto", method = RequestMethod.POST)
    public String account(Authentication authentication, @RequestParam MultipartFile image) throws IOException {
        String save = authentication.getName();
        String save2 = authentication.getName();
        String save3 = authentication.getName();
        String save4 = authentication.getName();
        String save5 = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(System.out::println);
        if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"))) {
            User byUsername = userService.findByUsername(save);
            Students students1 = byUsername.getStudents();
            String path = System.getProperty("user.home")
                    + File.separator
                    + "images"
                    + File.separator
                    + image.getOriginalFilename();

            image.transferTo(new File(path));
            students1.setAvatar(image.getOriginalFilename());
            studentsService.save(students1);

        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_CLASSTEACHERS"))) {
            User byUsername = userService.findByUsername(save2);
            Classteachers classteachers1 = byUsername.getClassteachers();
            String path = System.getProperty("user.home")
                    + File.separator
                    + "images"
                    + File.separator
                    + image.getOriginalFilename();

            image.transferTo(new File(path));
            classteachers1.setAvatar(image.getOriginalFilename());
            classteachersService.save(classteachers1);
        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_TEACHER"))) {
            User byUsername = userService.findByUsername(save3);
            Teachers teachers1 = byUsername.getTeachers();
            String path = System.getProperty("user.home")
                    + File.separator
                    + "images"
                    + File.separator
                    + image.getOriginalFilename();

            image.transferTo(new File(path));
            teachers1.setAvatar(image.getOriginalFilename());
            teachersService.save(teachers1);
        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_PARENT"))) {
            User byUsername = userService.findByUsername(save4);
            Parents parents1 = byUsername.getParents();
            String path = System.getProperty("user.home")
                    + File.separator
                    + "images"
                    + File.separator
                    + image.getOriginalFilename();

            image.transferTo(new File(path));
            parents1.setAvatar(image.getOriginalFilename());
            parentsService.save(parents1);
        } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_DEPUTI"))) {
            User byUsername = userService.findByUsername(save5);
            Deputy deputy1 = byUsername.getDeputy();
            String path = System.getProperty("user.home")
                    + File.separator
                    + "images"
                    + File.separator
                    + image.getOriginalFilename();

            image.transferTo(new File(path));
            deputy1.setAvatar(image.getOriginalFilename());
            deputyService.save(deputy1);
        }
        return "redirect:/account";
    }


    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}

