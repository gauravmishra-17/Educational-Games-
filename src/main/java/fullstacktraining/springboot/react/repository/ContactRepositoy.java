package fullstacktraining.springboot.react.repository;

import fullstacktraining.springboot.react.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepositoy extends JpaRepository<Contact,Long> {
}
