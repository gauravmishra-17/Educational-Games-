package fullstacktraining.springboot.react.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Game {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Title cannot be empty")
    @Schema(description = "Title of a game", example = "123", required = true)
    private String title;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Description cannot be empty")
    @Schema(description = "Description of a game", example = "This is a nice game to paly with the whoe family" +
            "", required = true)
    private String description;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Game URL cannot be empty")
    @Schema(description = "Picture's URL", example = "https://html5.gamedistribution.com/3126fc66d7be43218337ebdaf4071033/jpg", required = true)
    private String pictureUrl;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Genre cannot be empty")
    @Schema(description = "Genre of a game", example = "Action", required = true)
    private String genre;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Game URL cannot be empty")
    @Schema(description = "Game's URL", example = "https://html5.gamedistribution.com/3126fc66d7be43218337ebdaf4071033/", required = true)
    private String gameUrl;
}
