package ligai.models;

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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ingredient_effect",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "effect_id")
    )
    private List<Effect> effects = new ArrayList<>();

    @Builder(builderMethodName = "ingredientBuilder")
    public Ingredient(Long id, String name, String description, int available, int cost, Type type, List<Effect> effects){
        super(id, name, description, available, cost, type);
        this.effects = effects;
    }
}
