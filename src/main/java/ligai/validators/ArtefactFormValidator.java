package ligai.validators;

import ligai.forms.ArtefactForm;
import ligai.forms.IngredientForm;
import ligai.models.Ingredient;
import ligai.models.Product;
import ligai.repositories.IngredientRepository;
import ligai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ArtefactFormValidator implements Validator {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(ArtefactForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {

        ArtefactForm artefactForm = (ArtefactForm) target;

        // получили/не получили пользователя
        Optional<Product> existedProduct = productRepository.findByName(artefactForm.getName());
        // если пользователь есть
        if (existedProduct.isPresent()) {
            errors.reject("bad.name", "Артефакт с таким именем уже существует!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "Пустое имя");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.description", "Пустое description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "available", "empty.available", "Пустое available");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "empty.cost", "Пустое cost");

    }
}
