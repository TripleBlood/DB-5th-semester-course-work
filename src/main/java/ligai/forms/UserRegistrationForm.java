package ligai.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {

    private String login;
    private String pass;
    private String name;
    private String status;
    private int magic_index;

}