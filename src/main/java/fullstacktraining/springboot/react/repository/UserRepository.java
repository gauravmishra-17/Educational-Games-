package fullstacktraining.springboot.react.repository;


import fullstacktraining.springboot.react.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(@Param("email") String email);

    Optional<User> findByUsername(String username);
}
