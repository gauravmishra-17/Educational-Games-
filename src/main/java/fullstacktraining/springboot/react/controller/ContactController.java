package fullstacktraining.springboot.react.controller;

import fullstacktraining.springboot.react.model.Contact;
import fullstacktraining.springboot.react.model.Game;
import fullstacktraining.springboot.react.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/contacts")
@Log4j2
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @Operation(summary = "List all received messages")
    @GetMapping(path = "/all")
    public ResponseEntity<List<Contact>> listMessages(){
        return ResponseEntity.ok(contactService.findAll());
    }

    @Operation(summary = "Get message filtered by email")
    @GetMapping(path = "/find/{email}")
    public ResponseEntity<Contact> findByEmail(@RequestParam(value = "email") @PathVariable String email) {
        return ResponseEntity.ok(contactService.findByEmail(email));
    }
}
