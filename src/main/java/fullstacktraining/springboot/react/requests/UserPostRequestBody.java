package fullstacktraining.springboot.react.requests;

import lombok.Data;

@Data
public class UserPostRequestBody {

    private String email;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    // private Role role;
}
