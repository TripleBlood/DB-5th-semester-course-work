package ligai.validators;

import ligai.forms.NewProdInRequestForm;
import ligai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewProdInRequestFormValidator implements Validator {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(NewProdInRequestFormValidator.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {

        NewProdInRequestForm newProdInRequestForm = (NewProdInRequestForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "empty.quantity", "Введите желаемое количество товара!");
    }
}
