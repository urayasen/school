package oktenweb.school.controllers;

import oktenweb.school.models.custom.*;
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

    @GetMapping("/saveStudents")
    public String saveStudent(Students students) {
//        System.out.println("user");
        studentsService.save(students);
        return "registration/registration-students";
    }

    @GetMapping("/saveDeputy")
    public String saveDeputy(Deputy deputy) {
//        System.out.println("user");
        deputyService.save(deputy);
        return "redirect:/";
    }

    @GetMapping("/saveTeachers")
    public String saveTeachers(Teachers teachers) {
//        System.out.println("user");
        teachersService.save(teachers);
        return "redirect:/";
    }

    @GetMapping("/saveParents")
    public String saveParents(Parents parents) {
//        System.out.println("user");
        parentsService.save(parents);
        return "redirect:/";
    }

    @GetMapping("/saveClassteachers")
    public String saveClassteachers(Classteachers classteachers) {
//        System.out.println("user");
        classteachersService.save(classteachers);
        return "redirect:/";
    }


    @GetMapping("/saveStudent")
    public String registrationStudents(Students students){

        studentsService.save(students);
        return "redirect:/";
    }




}
