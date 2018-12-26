package ligai.controllers;

import ligai.enums.IngredientType;
import ligai.forms.IngredientForm;
import ligai.forms.PotionForm;
import ligai.repositories.IngredientRepository;
import ligai.services.AuthenticationService;
import ligai.services.ProductService;
import ligai.validators.IngredientFormValidator;
import ligai.validators.PotionFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdminIngredientController {

    //Репозитории————————————————————————————————————————

    @Autowired
    private IngredientRepository ingredientRepository;

    //Сервисы————————————————————————————————————————

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientFormValidator ingredientFormValidator;


    //Валидация————————————————————————————————————————

    @InitBinder("ingredientForm")
    public void initPotionFormValidator(WebDataBinder binder) {
        binder.addValidators(ingredientFormValidator);
    }

    //Маппинг————————————————————————————————————————

    @GetMapping("/admin/newIngredient")
    public String retIngredientPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                @RequestParam Optional<String> error) {



        return "new_ingredient";
    }

    @PostMapping(value = "/admin/newIngredient")
    public String makeNewIngredient(@Valid @ModelAttribute("ingredientForm") IngredientForm ingredientForm,
                                BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/newIngredient";
        }

        productService.addIngredient(ingredientForm);

        return "redirect:/admin/newIngredient";
    }

    @GetMapping("/admin/listIngredient")
    public String retIngredientListPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                      @RequestParam Optional<String> error) {

        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "admin_ingredient_list";
    }
}
