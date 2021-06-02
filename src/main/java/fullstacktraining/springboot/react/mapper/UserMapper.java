package fullstacktraining.springboot.react.mapper;
import fullstacktraining.springboot.react.requests.UserPostRequestBody;
import fullstacktraining.springboot.react.requests.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    public abstract User toUser(UserPostRequestBody UserPostRequestBody);
//
//    public abstract User toUser(UserPutRequestBody UserPostRequestBody);
}
