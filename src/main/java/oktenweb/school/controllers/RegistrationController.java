package oktenweb.school.controllers;

import oktenweb.school.models.User;
import oktenweb.school.models.custom.*;
import oktenweb.school.service.UserService;
import oktenweb.school.service.customService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {


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
    UserService userService;


//
//    @GetMapping("/saveStudents")
//    public String saveStudent(Students students) {
////        System.out.println("user");
////        userService.loadUserByUsername()
////        userService.byId()
//        studentsService.save(students);
//        return "registration/registration-students";
//    }

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


        @GetMapping("/saveStudents")
        public String registrationStudents(Students students, int id){

            System.out.println("saveStudents ---------   " + id);
            User user = userService.byId(id);
            students.setUser(user);
            studentsService.save(students);
            return "registrationsElements";
        }


    }
