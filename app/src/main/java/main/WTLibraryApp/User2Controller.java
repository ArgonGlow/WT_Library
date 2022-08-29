package main.WTLibraryApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class User2Controller {

    @GetMapping("/user")
    public String getUser(){
        return "user";
    }
}
