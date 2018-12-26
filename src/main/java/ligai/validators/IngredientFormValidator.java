package ligai.validators;


import ligai.forms.IngredientForm;
import ligai.models.Ingredient;
import ligai.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class IngredientFormValidator implements Validator {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(IngredientForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {

        IngredientForm ingredientForm = (IngredientForm) target;

        // получили/не получили пользователя
        Optional<Ingredient> existedPotion = ingredientRepository.findByName(ingredientForm.getName());
        // если пользователь есть
        if (existedPotion.isPresent()) {
            errors.reject("bad.name", "Ингридиент с таким именем уже существует!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "Пустое имя");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.description", "Пустое description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "available", "empty.available", "Пустое available");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "empty.cost", "Пустое cost");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ingredientType", "empty.ingredientType", "Вы не выбрали тип интгридиента!");

    }
}
