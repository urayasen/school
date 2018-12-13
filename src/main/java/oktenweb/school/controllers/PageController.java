package oktenweb.school.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
//123
    @GetMapping("/admin/news")
    private String news()  { return "news"; }

    @GetMapping("/pages")
    private String pages(){
        return "hhh";
    }

    @GetMapping("/marks")
    private String marks(){
        return "marks";
    }

    @GetMapping("/chat")
    private String chat(

    ){
        return "chat";
    }

    @GetMapping("/homework")
    private String homework(){
        return "homework";
    }

    @GetMapping("/lesson")
    private String lesson(){
        return "lesson";
    }

    @GetMapping("/news")
    private String news1(){
        return "news";
    }

    @GetMapping("visited")
    private String visited(){
        return "visited";
    }

    @GetMapping("account")
    private String account(){
        return "account";
    }
}
