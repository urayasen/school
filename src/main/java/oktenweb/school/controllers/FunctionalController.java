package oktenweb.school.controllers;


import oktenweb.school.models.functional.Subjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FunctionalController {


//    @GetMapping("/registrationExam")
//    public String registrationExam(
//            Model model) {
//        List<Exam> subjects = subjectsService.findAll();
//        model.addAttribute("subjects", subjects);
//        return "registrationSubjects";
//    }
//
//    @GetMapping("/saveSubjects")
//    public String saveSubjects(@RequestParam Integer id, @RequestParam String name) {
//
//        Subjects subjects;
////        if (id == null) {
////            subjects = new Subjects();
////        } else {
////            subjects = new Subjects(name);
////        }
//        if (id == null) {
//            subjects = new Subjects(name);
//        } else {
//            subjects = new Subjects(id, name);
//        }
//        subjectsService.save(subjects);
//
//        return "redirect:/registrationSubjects";
//    }
//
//    @GetMapping("/editSub/{id}")
//    public String resolveSingleSubject(@PathVariable/*("id")*/ int id,
//                                       Model model) {
//        Subjects subjects = subjectsService.byIdSub(id);
//        model.addAttribute("subjects", subjects);
////        System.out.println(contact);
////        System.out.println(model);
//        return "registrationSubjects";
//    }
//
//    @GetMapping("/deleteSub/{id}")
//    public String deleteSub(@PathVariable Integer id) {
//        subjectsService.deleteById(id);
//        return "redirect:/registrationSubjects";
//    }
}
