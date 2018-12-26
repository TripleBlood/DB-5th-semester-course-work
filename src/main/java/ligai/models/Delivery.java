package ligai.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "accepted_id")
    private User acceptUser;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    @OneToMany(mappedBy = "delivery")
    private ArrayList<Delivery_product> delivery_products;
}
