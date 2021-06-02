package fullstacktraining.springboot.react.service;

import fullstacktraining.springboot.react.exception.BadRequestException;
import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.repository.GameRepository;
import fullstacktraining.springboot.react.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final Utils utils;
    private final GameRepository gameRepository;

    public Page<Game> listAll(Pageable pageable){
        return gameRepository.findAll(pageable);
    }

    public List<Game> listAllNonPageable() {
        return gameRepository.findAll();
    }

    public List<Game> findByTitle(String title){return gameRepository.findByTitle(title);}

    public Game findByIdOrThrowBadRequestException(long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Game not Found"));
    }

    @Transactional
    public Game save(Game game){
        return gameRepository.save(game);
    }

    public void delete(Long id){
        gameRepository.delete(findByIdOrThrowBadRequestException(id));
    }

     public Game update(Game game) {
       return gameRepository.save(game);
    }
}
