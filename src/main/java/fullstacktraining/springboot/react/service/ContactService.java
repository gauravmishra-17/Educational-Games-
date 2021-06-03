package fullstacktraining.springboot.react.service;

import fullstacktraining.springboot.react.model.Contact;
import fullstacktraining.springboot.react.repository.ContactRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepositoy contactRepositoy;

    @Transactional
    public Contact save(Contact contact) {
        return contactRepositoy.save(contact);
    }

    public List<Contact> findAll() {
        return contactRepositoy.findAll();
    }

    public Contact findByEmail(String email){
        return  contactRepositoy.findByEmail(email);
    }
}
