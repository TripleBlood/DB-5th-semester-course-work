package ligai.controllers;


import ligai.models.Savings;
import ligai.repositories.ProductRepository;
import ligai.repositories.SavingsRepository;
import ligai.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    @GetMapping("/admin")
    public String getAdminHomePage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                   @RequestParam Optional<String> error) {
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        return "admin_home";
    }

    @GetMapping("/admin/list")
    public String getAdminProdListPAge(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                   @RequestParam Optional<String> error) {
        model.addAttribute("products", productRepository.findAll());
        return "admin_list";
    }

    @GetMapping("/admin/savings")
    public String getAdminProdListPAge(@ModelAttribute("model") ModelMap model) {
        long i = 1;
        Savings save = savingsRepository.getById(i);
        model.addAttribute("savings", save);
        return "admin_save";
    }

}
