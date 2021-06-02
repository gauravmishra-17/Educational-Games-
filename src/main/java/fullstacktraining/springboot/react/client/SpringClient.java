package fullstacktraining.springboot.react.client;

import fullstacktraining.springboot.react.model.Game;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Log4j2
public class SpringClient {

    public static void main(String[] args){

        ResponseEntity<Game> entity =
        new RestTemplate().getForEntity("http://localhost:8080/games/{id}", Game.class, 2);
        log.info(entity);

        Game game =  new RestTemplate().getForObject("http://localhost:8080/games/{id}", Game.class, 3);
        log.info(game);

        Game[] games =  new RestTemplate().getForObject("http://localhost:8080/games/all", Game[].class);
        log.info(Arrays.toString(games));

        ResponseEntity<List<Game>> gamesExchange =  new RestTemplate().exchange("http://localhost:8080/games/all", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Game>>() {});

        log.info(gamesExchange.getBody());

    }
}
