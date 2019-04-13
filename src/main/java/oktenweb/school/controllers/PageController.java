package oktenweb.school.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
//123

    @GetMapping("/marks")
    private String marks(){
        return "functional/marks";
    }



    @GetMapping("/homework")
    private String homework(){
        return "functional/homework";
    }


@GetMapping("/index")
public String index(){
    return "main/index";
}



}
