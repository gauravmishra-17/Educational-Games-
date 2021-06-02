package fullstacktraining.springboot.react.requests;

import lombok.Data;

@Data
public class GamePutRequestBody {
    private Long id;
    private String title;
    private String description;
    private String pictureUrl;
    private String genre;
    private String gameUrl;
}
