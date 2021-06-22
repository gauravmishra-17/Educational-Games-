package fullstacktraining.springboot.react.service;

import fullstacktraining.springboot.react.exception.BadRequestException;
import fullstacktraining.springboot.react.model.MyUserDetails;
import fullstacktraining.springboot.react.model.User;
import fullstacktraining.springboot.react.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;

@Service
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User savedUser = findUserByUsername(username);
        return new MyUserDetails(savedUser);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);

    }


    public List<User> listAllNonPageable() {
        return userRepository.findAll();
    }

    public User findByIdOrThrowBadRequestException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not Found"));
    }

    public User update(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void delete(long id) {
        userRepository.delete(findByIdOrThrowBadRequestException(id));
    }


    public User findUserByEmail(@Email(message = "Please enter a valid e-mail address") String email) {
        return userRepository.findByEmail(email);
    }
}
