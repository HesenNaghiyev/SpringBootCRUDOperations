package ada.wm2.jpa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomePageController {
    @GetMapping("/")
    public String getChoice(){
        return "welcome";
    }
}
