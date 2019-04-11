package oktenweb.school.controllers;

import oktenweb.school.models.Role;
import oktenweb.school.models.functional.Classes;
import oktenweb.school.models.functional.Subjects;
import oktenweb.school.models.User;
import oktenweb.school.models.custom.*;
import oktenweb.school.models.functional.ListSubjects;
import oktenweb.school.service.UserService;
import oktenweb.school.service.customService.*;
import oktenweb.school.service.functionalService.ClassJournalService;
import oktenweb.school.service.functionalService.ClassesService;
import oktenweb.school.service.functionalService.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Autowired
    ClassJournalService classJournalService;


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
        } else if (user1.getRole() == Role.ROLE_CLASSTHEACHER) {
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

    @GetMapping("/saveStudents")
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

        return "redirect:registrationFunction/registrationClasses";
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
                if (arrayStringElements[i].contains("stringlistElementsOne=") ) {
                    s = arrayStringElements[i].replaceAll("stringlistElementsOne=", "");
                    if (!s.equals("")) {
                        arraySingleElements.add(s);
                    }
                } else {
                    s = arrayStringElements[i].replaceAll("stringlistElementsTwo=", "");
                    if (!s.equals("")){
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
                    parents = parentsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                    List<Students> students1 = parents.getStudents();
                    Iterator<Students> iterator = students1.iterator();
                    while (iterator.hasNext()) {
                             students = iterator.next();
                             students.setParents(null);
                             studentsService.save(students);
                    }
                    System.out.println(parents);
                } else {
                    students = studentsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                    System.out.println(students);
                    students.setParents(parents);
                    studentsService.save(students);
                }
            }

            }else if (arrayStringElements[0].equals("elementsOne=classteachers") && (arrayStringElements[1].equals("elementsTwo=classes"))) {
                Classteachers classteachers = null;
                Classes classes = null;

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        classteachers = classteachersService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(classteachers);
                        classes = classteachers.getClasses();
                        System.out.println(classes);
                        if (classes==null ) continue;
                        classes.setClassteachers(null);
                        classesService.save(classes);
                    } else {
                        classes = classesService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        classes.setClassteachers(classteachers);
                        classesService.save(classes);

                    }
                }

            }else if (arrayStringElements[0].equals("elementsOne=classteachers") && (arrayStringElements[1].equals("elementsTwo=students"))) {
                Classteachers classteachers = null;
                Students students = null;

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        classteachers = classteachersService.byId(Integer.parseInt( arraySingleElements.get(i)));
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
                        students = studentsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(students);
                        students.setClassteachers(classteachers);
                        studentsService.save(students);
                    }
                }

            }else if (arrayStringElements[0].equals("elementsOne=classteachers") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
                Classteachers classteachers = null;
                Subjects subjects = null;

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        classteachers = classteachersService.byId(Integer.parseInt( arraySingleElements.get(i)));
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
                        subjects = subjectsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(subjects);
                        subjects.setClassteachers(classteachers);
                        subjectsService.save(subjects);
                    }
                }

            }else if (arrayStringElements[0].equals("elementsOne=deputy") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
                Deputy deputy = null;
                Subjects subjects = null;

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        deputy = deputyService.byId(Integer.parseInt( arraySingleElements.get(i)));
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
                        subjects = subjectsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(subjects);
                        subjects.setDeputy(deputy);
                        subjectsService.save(subjects);
                    }
                }

            }else if (arrayStringElements[0].equals("elementsOne=teachers") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
                Teachers teachers = null;
                Subjects subjects = null;

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        teachers = teachersService.byId(Integer.parseInt( arraySingleElements.get(i)));
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
                        subjects = subjectsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(subjects);
                        subjects.setTeachers(teachers);
                        subjectsService.save(subjects);
                    }
                }

            }else if (arrayStringElements[0].equals("elementsOne=classes") && (arrayStringElements[1].equals("elementsTwo=students"))) {
                Classes classes = null;
                Students students = null;

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        classes = classesService.byId(Integer.parseInt( arraySingleElements.get(i)));
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
                        students = studentsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(students);
                        students.setClasses(classes);
                        studentsService.save(students);
                    }
                }

            }else if (arrayStringElements[0].equals("elementsOne=classes") && (arrayStringElements[1].equals("elementsTwo=subjects"))) {
                Classes classes = null;
                Subjects subjects = null;
                List<Subjects> subjects1 = new ArrayList<>();

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        classes = classesService.byId(Integer.parseInt( arraySingleElements.get(i)));

                    } else {
                        subjects = subjectsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(subjects);
                        subjects1.add(subjects);
//
                    }
                }

            classes.setSubjects(subjects1);
            classesService.save(classes);


            }else if (arrayStringElements[0].equals("elementsOne=subjects") && (arrayStringElements[1].equals("elementsTwo=classes"))) {
                Classes classes = null;
                Subjects subjects = null;
                List<Classes> classes1 = new ArrayList<>();

                for (int i = 0; i < arraySingleElements.size(); i++) {
                    if (i == 0) {
                        subjects = subjectsService.byId(Integer.parseInt( arraySingleElements.get(i)));
                    } else {
                        classes = classesService.byId(Integer.parseInt( arraySingleElements.get(i)));
                        System.out.println(classes);
                        classes1.add(classes);

                    }
                }

            subjects.setClasses(classes1);
            subjectsService.save(subjects);

            }



        return "111";
    }


    @GetMapping("/SelectElements")
    public String selectSubjects(Model model){
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
                                    Model model){

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
                                  Model model){



        return "marks";
    }


    @GetMapping ("/schedule")
    public String schedule (Model model){
        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes" , classes);
        return "functional/schedule";
    }

    @GetMapping ("/saveSchedule")
    public String saveSchedule(@RequestParam("classes") int classes_id,
            Model model){

        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes" , classes);

        Classes classed = classesService.byId(classes_id);

        System.out.println(classed);

        List<Subjects> subjectsList = classed.getSubjects();

        model.addAttribute("subjects", subjectsList);


        return "functional/schedule";
    }

    @GetMapping("/changeSubjects/{id}")
    public @ResponseBody
    Teachers changeSubjects (@PathVariable/*("id")*/ int id){


        System.out.println("id=" + id);
        Subjects subjects = subjectsService.byId(id);
        System.out.println(subjects);

        Teachers teachers = subjects.getTeachers();
        System.out.println(teachers);
//        model.addAttribute("teacher", teachers.getName());
        return teachers;
    }

    @GetMapping("/account")
    @ResponseBody
    public Object currentUserName(Authentication authentication, Model model) {


            System.out.println(authentication);
            System.out.println(authentication.getName());
            User user = userService.findByUsername(authentication.getName());
//            Deputy deputy = deputyService.findByUsername(authentication.getName());
        model.addAttribute("name", user.getClassteachers().getName());
        model.addAttribute("phone", user.getClassteachers().getPhone());
        return "functional/account";
    }





//    @GetMapping(value = "/account")
//    @ResponseBody
//    public Object currentUserName(Authentication authentication) {
//
//        if (authentication != null) {
//            System.out.println(authentication);
//            System.out.println(authentication.getName());
//            User user = userService.findByUsername(authentication.getName());
////            Deputy deputy = deputyService.findByUsername(authentication.getName());
//            user.getClassteachers();
//            return null;
//        } else {
//            return "";
//        }
//    }

}
