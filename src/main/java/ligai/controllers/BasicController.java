package ligai.controllers;

import ligai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BasicController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/test")
    public String login(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("products", productRepository.findAll());
        return "shop_basic";
    }


}
