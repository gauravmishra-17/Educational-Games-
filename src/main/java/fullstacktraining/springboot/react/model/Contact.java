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
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Firstname cannot be empty")
    @Schema(description = "Firstname of user that will sent message", example = "Eduard", required = true)
    private String firstname;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Lastname cannot be empty")
    @Schema(description = "Lastname of user that will sent message", example = "Johansky", required = true)
    private String lastname;


    @Column(nullable = false)
    @NotNull
    @Email(message = "Please enter a valid e-mail address")
    @Schema(description = "Email of user that will sent message", example = "user@mail.com", required = true)
    private String email;

    @Column(length = 500)
    @NotNull
    @NotEmpty(message = "Message cannot be empty")
    @Schema(description = "Message of user that will sent message", example = "random message", required = true)
    private String message;
}
