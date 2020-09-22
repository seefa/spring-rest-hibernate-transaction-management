package ir.seefa.tutorial.spring.controller;

import ir.seefa.tutorial.spring.entity.ContactEntity;
import ir.seefa.tutorial.spring.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 13:24:21
 */
// 1. Read ir.seefa.tutorial.spring-core-tutorial and ir.seefa.tutorial.spring-jdbc-mvc-tutorial codes before starting this project because primary annotations and mvc logic explained there
// 2. Introduce a Bean to Spring with @RestController annotation for get Client HTTP REST requests.
@RestController
@RequestMapping("/api")
public class ContactRestController {
    // 3. wiring Controller bean to Service bean layer
    @Autowired
    ContactService contactService;

    // 4. Spring solution to handle some specific UI data models to understandable Java object(convert String date format to java.util.Date object). useful for marshalling purpose
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    // 5. handle HTTP Get requests from /api path and inner path to get list of contacts
    @GetMapping(value = "/getContacts/")
    public ResponseEntity<List<ContactEntity>> getContacts() {
        List<ContactEntity> contacts = contactService.getAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping(value = "/getContactsWithPagingAndSorting/{page}/{count}")
    public ResponseEntity<Page<ContactEntity>> getContacts(@PathVariable("page") int page, @PathVariable("count") int count) {
        Page<ContactEntity> contacts = contactService.getAllWithPagingAndSorting(page, count);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageableContacts/{page}")
    public ResponseEntity<Page<ContactEntity>> getPageableContacts(@PathVariable("page") int page, @RequestParam("count") Integer count) {
        int numberOfItemPerPage = count != null ? count : 10;
        Page<ContactEntity> contacts = contactService.getAllWithPagingAndSorting(page, numberOfItemPerPage);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // 6. save new contact information with POST request
    @PostMapping(value = "/createOrUpdateContact")
    public ResponseEntity<ContactEntity> saveContact(@RequestBody ContactEntity contact) {
        try {
            contactService.addOrUpdateContact(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 7. Delete a contact record with GET request and send contact_id with URL query param
    @DeleteMapping(value = "/deleteContact/{id}")
    public ResponseEntity deleteContact(@PathVariable("id") int contactId) {
        try {
            contactService.delete(contactId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
