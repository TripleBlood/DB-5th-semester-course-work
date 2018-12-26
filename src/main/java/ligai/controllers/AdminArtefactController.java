package ligai.controllers;


import ligai.enums.Type;
import ligai.forms.ArtefactForm;
import ligai.forms.IngredientForm;
import ligai.repositories.ProductRepository;
import ligai.services.AuthenticationService;
import ligai.services.ProductService;
import ligai.validators.ArtefactFormValidator;
import ligai.validators.IngredientFormValidator;
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
public class AdminArtefactController {

    //Репозитории————————————————————————————————————————

    @Autowired
    private ProductRepository productRepository;

    //Сервисы————————————————————————————————————————

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ArtefactFormValidator artefactFormValidator;


    //Валидация————————————————————————————————————————

    @InitBinder("artefactForm")
    public void initArtefactFormValidator(WebDataBinder binder) {
        binder.addValidators(artefactFormValidator);
    }

    //Маппинг————————————————————————————————————————

    @GetMapping("/admin/newArtefact")
    public String retArtefactPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                    @RequestParam Optional<String> error) {

        model.addAttribute("artefacts", productRepository.findAllByType(Type.ARTEFACT));

        return "new_Artefact";
    }

    @PostMapping(value = "/admin/newArtefact")
    public String makeNewArtefact(@Valid @ModelAttribute("artefactForm") ArtefactForm artefactForm,
                                    BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/newArtefact";
        }

        productService.addArtefact(artefactForm);

        return "redirect:/admin/newArtefact";
    }


    @GetMapping("/admin/listArtefact")
    public String retArtefactListPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                  @RequestParam Optional<String> error) {

        model.addAttribute("artefacts", productRepository.findAllByType(Type.ARTEFACT));

        return "admin_artefact_list";
    }
}
