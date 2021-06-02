package fullstacktraining.springboot.react.utils;

import fullstacktraining.springboot.react.exception.ResourceNotFoundException;
import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.repository.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public Game findGameOrThrowNotFound(Long id, GameRepository gameRepository) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unfortunatly no user was found with id nr " + id));

    }
}
