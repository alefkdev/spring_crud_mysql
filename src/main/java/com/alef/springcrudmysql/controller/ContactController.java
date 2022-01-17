package com.alef.springcrudmysql.controller;

import com.alef.springcrudmysql.model.Contact;
import com.alef.springcrudmysql.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Since there's no business's logic in this project,
//I'm not using a service layer.

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository contactRepository){
        this.repository = contactRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return repository.save(contact);
    }

    //Not recommended to use the JPA entity as a DTO.
    //I'm just doing this as a shortcut (since the idea of this project is to study the crud endpoints)
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Contact contact) {
        return repository.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Contact updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
