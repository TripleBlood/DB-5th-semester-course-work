package ligai.validators;

import ligai.forms.PotionForm;
import ligai.models.Potion;
import ligai.repositories.PotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PotionFormValidator implements Validator {
    @Autowired
    private PotionRepository potionRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(PotionForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {

        PotionForm potionForm = (PotionForm) target;

        // получили/не получили пользователя
        Optional<Potion> existedPotion = potionRepository.findByName(potionForm.getName());
        // если пользователь есть
        if (existedPotion.isPresent()) {
            errors.reject("bad.name", "Зелье с таким именем уже существует!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "Пустое имя");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.description", "Пустое description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "available", "empty.available", "Пустое available");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "empty.cost", "Пустое cost");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "volume", "empty.volume", "Пустое volume");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "catalystIng", "empty.catalystIng", "Ничего не выбрано! 1");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "essenceIng", "empty.essenceIng", "Пустое ыфвафыва");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conservatorIng", "empty.conservatorIng", "Пустое conservatorIng");

    }
}
