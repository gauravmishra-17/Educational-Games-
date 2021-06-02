package fullstacktraining.springboot.react.integration;

import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.repository.GameRepository;
import fullstacktraining.springboot.react.util.GameCreator;
import fullstacktraining.springboot.react.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration
class GameControllerIT {

    @Autowired
    @Qualifier(value = "testRestTemplateRoleUser")
    private TestRestTemplate testRestTemplateRoleUser;

    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplateRoleAdmin;
    @MockBean
    private GameRepository gameRepositoryMock;

    @Lazy
    @TestConfiguration
    static class Config {

        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port)
                    .basicAuthentication("rafael", "myPassword");
            return new TestRestTemplate(restTemplateBuilder);
        }

        @Bean(name = "testRestTemplateRoleAdmin")
        public TestRestTemplate testRestTemplateRoleAdminCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port)
                    .basicAuthentication("nema", "myPassword");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @BeforeEach
    public void setUp(){

        PageImpl<Game> gamePage = new PageImpl<>(List.of(GameCreator.createValidGame()));
        BDDMockito.when(gameRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(gamePage);

        BDDMockito.when(gameRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(GameCreator.createValidGame()));

        BDDMockito.when(gameRepositoryMock.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(List.of(GameCreator.createValidGame()));

        BDDMockito.when(gameRepositoryMock.save(GameCreator.createGameToBeSaved()))
                .thenReturn(GameCreator.createValidGame());

        BDDMockito.doNothing().when(gameRepositoryMock).delete(ArgumentMatchers.any(Game.class));

        BDDMockito.when(gameRepositoryMock.save(GameCreator.createValidGame()))
                .thenReturn(GameCreator.createValidUpdatedGame());
    }

    @Test
    @DisplayName("list returns list of games inside page object when successful")
    public void listAll_ReturnsListOfGamesInsidePageObject_WhenSuccessful(){

        String expectedTitle = GameCreator.createValidGame().getTitle();

        //@formatter:off
        Page<Game> gamesPage = testRestTemplateRoleUser.exchange("/games", HttpMethod.GET,
                null, new ParameterizedTypeReference<PageableResponse<Game>>() {}).getBody();
        //@formatter:on


        Assertions.assertThat(gamesPage).isNotNull();

        Assertions.assertThat(gamesPage.toList()).isNotEmpty();

        Assertions.assertThat(gamesPage.toList().get(0).getTitle()).isEqualTo(expectedTitle);

    }

    @Test
    @DisplayName("findById returns forbidden when user has no admin role")
    public void findById_Returns403_WhenUserIsNotAdmin(){

        ResponseEntity<Game> response =  testRestTemplateRoleUser.getForEntity("/games/admin/1", Game.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    @DisplayName("findById returns a game when succesful")
    public void findById_ReturnsListOfGames_WhenSuccessful(){

        Long expectedId = GameCreator.createValidGame().getId();

        Game game = testRestTemplateRoleAdmin.getForObject("/games/admin/1", Game.class);

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByTitle returns a list of games when succesful")
    public void findByTitle_ReturnsListOfGames_WhenSuccessful(){

        String expectedTitle = GameCreator.createValidGame().getTitle();

        //@formatter:off
        List<Game> gamesList = testRestTemplateRoleUser.exchange("/games/find?title='Mandala'", HttpMethod.GET,
                null, new ParameterizedTypeReference< List<Game>>() {}).getBody();
        //@formatter:on

        Assertions.assertThat(gamesList).isNotNull();

        Assertions.assertThat(gamesList).isNotEmpty();

        Assertions.assertThat(gamesList.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("save returns forbidden when user has no admin role")
    public void save_Returns403_WhenUserIsNotAdmin(){

        Game gameToBeSaved = GameCreator.createGameToBeSaved();

        Game game = testRestTemplateRoleUser.exchange("/games/admin", HttpMethod.POST,
                createJsonHttpEntity(gameToBeSaved), Game.class).getBody();

        ResponseEntity<Game> response =  testRestTemplateRoleUser.getForEntity("/games/admin", Game.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }

    @Test
    @DisplayName("save returns game when successful")
    public void save_SavesGameAndReturn200Status_WhenUserIsAdmin(){

        Game gameToBeSaved = GameCreator.createGameToBeSaved();

        Game game = testRestTemplateRoleAdmin.exchange("/games/admin", HttpMethod.POST,
                createJsonHttpEntity(gameToBeSaved), Game.class).getBody();

        Long expectedId = game.getId();

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getId()).isEqualTo(expectedId);

    }
    @Test
    @DisplayName("delete removes an game when succesful")
    public void delete_RemovesGames_WhenSuccessful(){

         ResponseEntity<Void> responseEntity = testRestTemplateRoleAdmin.exchange("/games/admin/1", HttpMethod.DELETE,
                 null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("delete returns forbidden when user has no admin role")
    public void delete_Returns403_WhenUserIsNotAdmin(){

        ResponseEntity<Void> responseEntity = testRestTemplateRoleUser.exchange("/games/admin/1", HttpMethod.DELETE,
                null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }

    @Test
    @DisplayName("update save updated game when succesful")
    public void update_SaveUpdatedGame_WhenSuccessful(){

        Game validGame = GameCreator.createValidGame();

        ResponseEntity<Void> responseEntity = testRestTemplateRoleAdmin.exchange("/games/admin", HttpMethod.PUT,
                createJsonHttpEntity(validGame), Void.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("update returns forbidden when user has no admin role")
    public void update_Returns403_WhenUserIsNotAdmin(){

        ResponseEntity<Void> responseEntity = testRestTemplateRoleUser.exchange("/games/admin/1", HttpMethod.PUT,
                null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }
    private HttpEntity<Game> createJsonHttpEntity(Game game){
        return new HttpEntity<>(game, createJsonHeader());
    }
    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
