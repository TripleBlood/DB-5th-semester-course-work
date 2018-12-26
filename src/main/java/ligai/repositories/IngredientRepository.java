package ligai.repositories;

import ligai.enums.IngredientType;
import ligai.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    ArrayList<Ingredient> findAllByIngredientType(IngredientType ingredientType);
    Ingredient findFirstByName(String name);

    Optional<Ingredient> findByName(String name);

}
