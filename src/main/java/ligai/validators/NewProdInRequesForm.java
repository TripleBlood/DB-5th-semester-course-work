package ligai.validators;

import ligai.forms.IngredientForm;
import ligai.models.Ingredient;
import ligai.repositories.IngredientRepository;
import ligai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

public class NewProdInRequesForm implements Validator {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(NewProdInRequesForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {

        NewProdInRequesForm newProdInRequesForm = (NewProdInRequesForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "empty.quantity", "Введите желаемое количество товара!");
    }
}
