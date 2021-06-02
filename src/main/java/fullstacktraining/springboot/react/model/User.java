package fullstacktraining.springboot.react.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Firstname cannot be empty")
    @Schema(description = "Name of website's user", example = "Eduard", required = true)
    private String firstname;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Lastname cannot be empty")
    @Schema(description = "Lastname of website's user", example = "Eduard", required = true)
    private String lastname;


    @Column(nullable = false)
    @NotNull
    @Email(message = "Please enter a valid e-mail address")
    @Schema(description = "Email of the user", example = "user@mail.com", required = true)
    private String email;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Username cannot be empty")
    @Schema(description = "Customized description of username", example = "eduard@someone.com", required = true)
    private String username;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    @Schema(description = "Password of a user to have access to books", example = "myPassword", required = true)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @Schema(description = "Role the user has to access the games platform", example = "Admin or User", required = true)
    private Role role;

}
