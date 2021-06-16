package fullstacktraining.springboot.react.controller;


import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.model.User;
import fullstacktraining.springboot.react.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<User> save(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.save(user));
    }


    @Operation(summary = "List all games unsorted")
    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> listAll() {
        return ResponseEntity.ok(userService.listAllNonPageable());
    }

    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<User> updateGame(@PathVariable Long id, @RequestBody User user) {

        User updatedUser = userService.findByIdOrThrowBadRequestException(id);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());

        User updatedGame = userService.update(updatedUser);
        return ResponseEntity.ok(updatedGame);
    }

    @Operation(summary = "Delete Game")
    @DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "resource deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find game by id")
    @GetMapping(path = "{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findByIdOrThrowBadRequestException(id));
    }
}
