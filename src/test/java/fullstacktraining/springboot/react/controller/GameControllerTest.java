package fullstacktraining.springboot.react.controller;
import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.service.GameService;
import fullstacktraining.springboot.react.util.GameCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class GameControllerTest {

    @InjectMocks
    private GameController gameController;
    @Mock
    private GameService gameServiceMock;

    @BeforeEach
    public void setUp(){
        PageImpl<Game> gamePage = new PageImpl<>(List.of(GameCreator.createValidGame()));
        BDDMockito.when(gameServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(gamePage);

        BDDMockito.when(gameServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(GameCreator.createValidGame());

        BDDMockito.when(gameServiceMock.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(List.of(GameCreator.createValidGame()));

        BDDMockito.when(gameServiceMock.save(GameCreator.createGameToBeSaved()))
                .thenReturn(GameCreator.createValidGame());

        BDDMockito.doNothing().when(gameServiceMock).delete(ArgumentMatchers.anyLong());

        BDDMockito.when(gameServiceMock.save(GameCreator.createValidGame()))
                .thenReturn(GameCreator.createValidUpdatedGame());
    }

    @Test
    @DisplayName("listAll returns a pageable list of games when succesful")
    public void listAll_ReturnsListOfGamesInsidePageObject_WhenSuccessful(){

        String expectedTitle = GameCreator.createValidGame().getTitle();

        Page<Game> gamePage = gameController.list(null).getBody();

        Assertions.assertThat(gamePage).isNotNull();

        Assertions.assertThat(gamePage.toList()).isNotEmpty();

        Assertions.assertThat(gamePage.toList().get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("findById returns a game when succesful")
    public void findById_ReturnsListOfGames_WhenSuccessful(){

        Long expectedId = GameCreator.createValidGame().getId();

       Game game = gameController.findById(1l).getBody();

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByTitle returns a list of games when succesful")
    public void findByTitle_ReturnsListOfGames_WhenSuccessful(){

        String expectedTitle = GameCreator.createValidGame().getTitle();

        List<Game> gamesList = gameController.findByTitle("random Name").getBody();

        Assertions.assertThat(gamesList).isNotNull();

        Assertions.assertThat(gamesList).isNotEmpty();

        Assertions.assertThat(gamesList.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("save creates an game when succesful")
    public void save_CreatesGames_WhenSuccessful(){

        Long expectedId = GameCreator.createValidGame().getId();
        Game gameToBeSaved = GameCreator.createGameToBeSaved();

        Game game = gameController.save(gameToBeSaved).getBody();

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes an game when succesful")
    public void delete_RemovesGames_WhenSuccessful(){

         ResponseEntity<Void> responseEntity = gameController.delete(1);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

//    @Test
//    @DisplayName("update save updated game when succesful")
//    public void update_SaveUpdatedGame_WhenSuccessful(){
//        Long id = GameCreator.createValidGame().getId();
//        ResponseEntity<Game> responseEntity = gameController.updateGame(id,GameCreator.createValidGame());
//
//        Assertions.assertThat(responseEntity).isNotNull();
//
//        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//
//        Assertions.assertThat(responseEntity.getBody()).isNull();
//    }

}
