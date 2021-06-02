package fullstacktraining.springboot.react.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "First name is required")
    private String firstname;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastname;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable = false)

    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
