package ligai.models;

import ligai.enums.IngredientType;
import ligai.enums.Type;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "ingredient")
public class Ingredient extends Product{

    private IngredientType ingredientType;

    @Builder(builderMethodName = "ingredientBuilder")
    public Ingredient(Long id, String name, String description, int available, int cost,IngredientType ingredientType){
        super(id, name, description, available, cost, Type.INGREDIENT);
        this.ingredientType = ingredientType;
    }
}
