package fullstacktraining.springboot.react.service;
import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.repository.GameRepository;
import fullstacktraining.springboot.react.util.GameCreator;
import fullstacktraining.springboot.react.utils.Utils;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepositoryMock;

    @Mock
    private Utils utilsMock;

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

        BDDMockito.when(utilsMock.findGameOrThrowNotFound(ArgumentMatchers.anyLong(),
                ArgumentMatchers.any(GameRepository.class)))
                .thenReturn(GameCreator.createValidUpdatedGame());
    }

    @Test
    @DisplayName("listAll returns a pageable list of games when succesful")
    public void listAll_ReturnsListOfGamesInsidePageObject_WhenSuccessful(){

        String expectedTitle = GameCreator.createValidGame().getTitle();

        Page<Game> gamePage = gameService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(gamePage).isNotNull();

        Assertions.assertThat(gamePage.toList()).isNotEmpty();

        Assertions.assertThat(gamePage.toList().get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("findById returns a game when succesful")
    public void findById_ReturnsListOfGames_WhenSuccessful(){

        Long expectedId = GameCreator.createValidGame().getId();

        Game game = gameService.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByTitle returns a pageable list of games when succesful")
    public void findByTitle_ReturnsGame_WhenSuccessful(){

        String expectedTitle = GameCreator.createValidGame().getTitle();

        List<Game> gamesList = gameService.findByTitle("random Name");

        Assertions.assertThat(gamesList).isNotNull();

        Assertions.assertThat(gamesList).isNotEmpty();

        Assertions.assertThat(gamesList.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("save creates an game when succesful")
    public void save_CreatesGames_WhenSuccessful(){

        Long expectedId = GameCreator.createValidGame().getId();
        Game gameToBeSaved = GameCreator.createGameToBeSaved();

        Game game = gameService.save(gameToBeSaved);

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes an game when succesful")
    public void delete_RemovesGames_WhenSuccessful(){
        Assertions.assertThatCode(() -> gameService.delete(1l)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("save updating update game when succesful")
    public void save_SaveUpdateGame_WhenSuccessful(){

        Game validUpdatedGame = GameCreator.createValidUpdatedGame();

        String expectedTitle = validUpdatedGame.getTitle();

        Game game = gameService.save(GameCreator.createValidGame());

        Assertions.assertThat(game).isNotNull();

        Assertions.assertThat(game.getId()).isNotNull();

        Assertions.assertThat(game.getTitle()).isEqualTo(expectedTitle);
    }

}
