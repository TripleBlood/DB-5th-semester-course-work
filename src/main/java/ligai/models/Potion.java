package ligai.models;

import ligai.enums.Type;
import lombok.*;

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
@Table(name = "potion")
public class Potion extends Product {

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "potion_ingredient",
            joinColumns = @JoinColumn(name = "potion_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @Builder(builderMethodName = "potionBuilder", builderClassName = "builder")
    public Potion(Long id, String name, String description, int available, int cost,  List<Ingredient> ingredients, double volume){
        super(id, name, description, available, cost, Type.POTION);
        this.ingredients = ingredients;
        this.volume = volume;
    }

    public static class PotionBuilder extends ProductBuilder{
        PotionBuilder() {
            super();
        }
    }

    private double volume;

    //По сути в табличке есть только одно поле, которое является и первичным и внешним на проодукт ключем
}
