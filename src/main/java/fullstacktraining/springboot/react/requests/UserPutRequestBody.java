package fullstacktraining.springboot.react.requests;

import lombok.Data;

@Data
public class UserPutRequestBody {

    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    //private Role role;
}
