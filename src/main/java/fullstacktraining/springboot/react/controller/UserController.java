package fullstacktraining.springboot.react.controller;


import fullstacktraining.springboot.react.config.security.SecurityConfig;
import fullstacktraining.springboot.react.model.*;
import fullstacktraining.springboot.react.service.UserService;
import fullstacktraining.springboot.react.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Import(SecurityConfig.class)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(path = "/register")
    public ResponseEntity<?> save(@RequestBody @Valid User user) {
        User userByUsername = userService.findUserByUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userByEmail = userService.findUserByEmail(user.getEmail());
        if (userByUsername != null || userByEmail != null) {
            Message message = new Message();
            message.setMsg("username already exists");
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } else
            return ResponseEntity.ok(userService.save(user));

    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.
                    authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authenticationRequest.getUsername(),
                                    authenticationRequest.getPassword()));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);
        String message;
        if (userService.findUserByUsername(authenticationRequest.getUsername()).getIsAdmin())
            message = "true";
        else message = "false";
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken, message));
    }


    @Operation(summary = "List all games unsorted")
    @GetMapping(path = "/admin/all")
    public ResponseEntity<List<User>> listAll(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(userService.listAllNonPageable());
    }

    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<User> updateGame(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id, @RequestBody User user) {

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
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String bearerToken, @PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find game by id")
    @GetMapping(path = "{id}")
    public ResponseEntity<User> findById(@RequestHeader("Authorization") String bearerToken, @PathVariable long id) {
        return ResponseEntity.ok(userService.findByIdOrThrowBadRequestException(id));
    }
}
