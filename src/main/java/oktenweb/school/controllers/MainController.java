package oktenweb.school.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
//123
    @GetMapping("/login")
    public String index() {
        return "main/login";
    }


    @PostMapping("/successURL")
    public String successURL() {
        System.out.println("You succes login");
        return "redirect:/account";
    }

}
