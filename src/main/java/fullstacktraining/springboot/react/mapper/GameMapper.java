package fullstacktraining.springboot.react.mapper;

import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.requests.GamePostRequestBody;
import fullstacktraining.springboot.react.requests.GamePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class GameMapper {

        public static final GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

        public abstract Game toGame(GamePostRequestBody gamePostRequestBody);

        public abstract Game toGame(GamePutRequestBody gamePostRequestBody);
}
