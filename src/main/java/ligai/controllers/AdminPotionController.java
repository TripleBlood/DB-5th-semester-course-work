package ligai.controllers;

import ligai.enums.IngredientType;
import ligai.forms.PotionForm;
import ligai.models.Ingredient;
import ligai.repositories.IngredientRepository;
import ligai.repositories.PotionRepository;
import ligai.services.AuthenticationService;
import ligai.services.ProductService;
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
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdminPotionController {

    //Репозитории

    @Autowired
    private PotionRepository potionRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    //————————————————————————————————————————
    //********//

    //Сервисы————————————————————————————————————————

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductService productService;

    //————————————————————————————————————————
    //********//

    //Валидаторы ————————————————————————————————————————

    @Autowired
    private PotionFormValidator potionFormValidator;

    //————————————————————————————————————————
    //********//

    //Валидация форм
    @InitBinder("potionForm")
    public void initPotionFormValidator(WebDataBinder binder) {
        binder.addValidators(potionFormValidator);
    }

    //————————————————————————————————————————
    //********//

    //Potion pages

    @GetMapping("/admin/newPotion")
    public String retPotionPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                        @RequestParam Optional<String> error) {


        model.addAttribute("catalystIngs", ingredientRepository.findAllByIngredientType(IngredientType.CATALYST));
        model.addAttribute("essenceIngs", ingredientRepository.findAllByIngredientType(IngredientType.ESSENCE));
        model.addAttribute("conservatorIngs", ingredientRepository.findAllByIngredientType(IngredientType.CONSERVATOR));
        return "new_potion";
    }

    @PostMapping(value = "/admin/newPotion")
    public String makeNewPotion(@Valid @ModelAttribute("userForm") PotionForm potionForm,
                         BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/newPotion";
        }

        productService.addPotion(potionForm);

        return "redirect:/admin/newPotion";
    }
    @GetMapping("/admin/listPotion")
    public String retPotionListPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                @RequestParam Optional<String> error) {

        model.addAttribute("ingredients", ingredientRepository.findAllByIngredientType(IngredientType.CATALYST));
        model.addAttribute("potions", potionRepository.findAll());

        return "admin_potion_list";
    }

    //————————————————————————————————————————
    //********//

}
