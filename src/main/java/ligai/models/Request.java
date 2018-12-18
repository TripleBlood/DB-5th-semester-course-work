package ligai.models;

import ligai.enums.Reqest_status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Reqest_status reqest_status;

    @Temporal(TemporalType.DATE)
    private Date creation_date;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date completion_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
