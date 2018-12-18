package ligai.models;

import ligai.enums.Type;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int available;
    private int cost;

    @Enumerated(EnumType.STRING)
    private Type type;
}
