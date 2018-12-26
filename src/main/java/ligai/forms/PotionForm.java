package ligai.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PotionForm {

    private String name;
    private String description;
    private int available;
    private int cost;
    private double volume;

    private String catalystIng;
    private String essenceIng;
    private String conservatorIng;

}
