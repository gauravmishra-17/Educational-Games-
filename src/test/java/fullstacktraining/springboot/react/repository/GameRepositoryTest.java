package fullstacktraining.springboot.react.repository;

import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.util.GameCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Game Repository Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
class GameRepositoryTest {

    // get services to be tested
    @Autowired
    private GameRepository gameRepository;

    @Test
    @DisplayName("Save creates game when successful")
    public void save_PersistGame_WhenSuccessful(){

        Game game = GameCreator.createGameToBeSaved();
        Game savedGame = this.gameRepository.save(game);

        Assertions.assertThat(savedGame.getId()).isNotNull();
        Assertions.assertThat(savedGame.getTitle()).isNotNull();
        Assertions.assertThat(savedGame.getDescription()).isNotNull();
        Assertions.assertThat(savedGame.getGenre()).isNotNull();
        Assertions.assertThat(savedGame.getPictureUrl()).isNotNull();
        Assertions.assertThat(savedGame.getGameUrl()).isNotNull();
        Assertions.assertThat(savedGame.getTitle()).isEqualTo(game.getTitle());
    }

    @Test
    @DisplayName("Save update game when successful")
    public void save_UpdateGame_WhenSuccessful(){

        Game game = GameCreator.createGameToBeSaved();
        Game savedGame = this.gameRepository.save(game);

        // update title and gameUrl
        savedGame.setTitle("A new title have being given");
        savedGame.setGameUrl("https://html5.gamedistribution.com/340f801248cd43cf99709a9aa957a12f2/");

        Game updatedGame = this.gameRepository.save(savedGame);

        Assertions.assertThat(savedGame.getId()).isNotNull();
        Assertions.assertThat(savedGame.getTitle()).isNotNull();
        Assertions.assertThat(savedGame.getDescription()).isNotNull();
        Assertions.assertThat(savedGame.getGenre()).isNotNull();
        Assertions.assertThat(savedGame.getPictureUrl()).isNotNull();
        Assertions.assertThat(savedGame.getGameUrl()).isNotNull();
        Assertions.assertThat(savedGame.getTitle()).isEqualTo(updatedGame.getTitle());
    }

    @Test
    @DisplayName("Delete removes game when successful")
    public void delete_RemoveGame_WhenSuccessful(){

        Game game = GameCreator.createGameToBeSaved();
        Game savedGame = this.gameRepository.save(game);

        // delete savedGame
        this.gameRepository.delete(savedGame);
        // Control if game was indeed deleted
        Optional<Game> gameOptional = this.gameRepository.findById(savedGame.getId());

        Assertions.assertThat(gameOptional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find by title returns game when successful")
    public void findByTitle_returnGame_WhenSuccessful(){

        Game game = GameCreator.createGameToBeSaved();
        Game savedGame = this.gameRepository.save(game);

        String title = savedGame.getTitle();

        List<Game> gameList = this.gameRepository.findByTitle(title);

        Assertions.assertThat(gameList).isNotEmpty();
        Assertions.assertThat(gameList).contains(savedGame);
    }

    @Test
    @DisplayName("Find by title returns empty list when game is not found")
    public void findByTitle_returnEmptyList_WhenGameNotFound(){

        String title = "Random Name";

        List<Game> gameList = this.gameRepository.findByTitle(title);

        Assertions.assertThat(gameList).isEmpty();

    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when title is empty")
    public void save_ThrowConstraintViolationException_WhenTitleIsEmpty(){

        Game game = new Game();
//        Assertions.assertThatThrownBy(()-> gameRepository.save(game))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.gameRepository.save(game))
                .withMessageContaining("Title cannot be empty");

    }

}
