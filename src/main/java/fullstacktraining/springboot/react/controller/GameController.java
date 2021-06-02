package fullstacktraining.springboot.react.controller;

import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.service.GameService;
import fullstacktraining.springboot.react.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/games")
@Log4j2
@RequiredArgsConstructor
public class GameController {

    private final Utils dateUtil;
    private final GameService gameService;

    @GetMapping
    @Operation(summary = "List all games paginated and sorted",
    description = "To use pagination and sort add the params ?page='number'&sort='field' to the url",
    tags ={"game"})
    public ResponseEntity<Page<Game>> list(Pageable pageable){
        return ResponseEntity.ok(gameService.listAll(pageable));
    }

    @Operation(summary = "List all games unsorted")
    @GetMapping(path = "/all")
    public ResponseEntity<List<Game>> listAll(){
        return ResponseEntity.ok(gameService.listAllNonPageable());
    }

     @Operation(summary = "Find game by id")
    @GetMapping(path = "{id}")
    public ResponseEntity<Game> findById(@PathVariable long id) {
        return ResponseEntity.ok(gameService.findByIdOrThrowBadRequestException(id));
    }

    @Operation(summary = "Get list of games by title")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Game>> findByTitle(@RequestParam(value = "title") String title) {
        return ResponseEntity.ok(gameService.findByTitle(title));
    }

    @Operation(summary = "Save Game to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Forbidden: Authentication failure")
    })
    @PostMapping(path = "/admin")
    public ResponseEntity<Game> save(@RequestBody @Valid Game game) {
        return ResponseEntity.ok(gameService.save(game));
    }

    @Operation(summary = "Delete Game")
    @DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "resource deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     @Operation(summary = "Update Game details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "resource updated successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden: Authentication failure")
    })
    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game gameDetails) {

        Game game = gameService.findByIdOrThrowBadRequestException(id);
        game.setTitle(gameDetails.getTitle());
        game.setDescription(gameDetails.getDescription());
        game.setPictureUrl(gameDetails.getPictureUrl());
        game.setGenre(gameDetails.getGenre());
        game.setGameUrl(gameDetails.getGameUrl());

        Game updatedGame = gameService.update(game);
        return ResponseEntity.ok(updatedGame);
    }

}
