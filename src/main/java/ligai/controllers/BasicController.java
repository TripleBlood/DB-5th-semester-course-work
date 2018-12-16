package ligai.controllers;

import ligai.models.User;
import ligai.repositories.UsersRepository;
import ligai.services.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BasicController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/test")
    public String login(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("users", usersRepository.findAll());
        for (User user: usersRepository.findAll()) {
            System.out.println(user.getId() + " " + user.getLogin() + " " + user.getName());
        }
        return "test";
    }
}
