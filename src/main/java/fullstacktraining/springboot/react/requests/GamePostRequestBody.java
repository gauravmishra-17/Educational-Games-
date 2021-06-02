package fullstacktraining.springboot.react.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GamePostRequestBody {

    @NotEmpty(message = "The game title cannot be empty")
    private String title;
    private String description;
    private String pictureUrl;
    private String genre;
    private String gameUrl;

}
