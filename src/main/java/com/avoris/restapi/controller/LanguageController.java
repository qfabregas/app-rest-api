package com.avoris.restapi.controller;

import com.avoris.restapi.exception.ResourceNotFoundException;
import com.avoris.restapi.service.LanguageService;
import com.avoris.restapi.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app-rest-api/v1")
public class LanguageController {

    @Autowired
    private LanguageService service;

    // RESTfull API methods for Retrieval operations
    @GetMapping("/languages")
    public List<Language> list() {
        return service.listAll();
    }

    @GetMapping("/languages/{id}")
    public ResponseEntity<Language> get(@PathVariable int id) throws ResourceNotFoundException {
        try {
            Language idioma = service.get(id);
            return new ResponseEntity<Language>(idioma, HttpStatus.OK);
        } catch (Exception e) {
            //return new ResponseEntity<Language>(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Language not found for this id : " + id);
        }
    }

    // RESTfull API methods for Create operations
    @PostMapping("/languages")
    public Language add(@Valid @RequestBody Language value) {
        return service.save(value);
    }

    // RESTfull API methods for Update operations
    /*
    @PutMapping("/languages/{id}")
    public ResponseEntity<?> update(@RequestBody Language value, @PathVariable int id) {
        try {
            Language existIdioma = service.get(id);
            service.save(value);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @PutMapping("/languages/{id}")
    public ResponseEntity<?> update(@RequestBody Language value, @PathVariable int id) throws ResourceNotFoundException {
        try {
            service.get(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Language not found for this id : " + id);
        }
        service.save(value);
        return new ResponseEntity<Language>(value, HttpStatus.OK);
    }

    // RESTfull API methods for Delete operations
    /*
    @DeleteMapping("/languages/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    */
    @DeleteMapping("/languages/{id}")
    public Map<String, Boolean> delete(@PathVariable int id) throws ResourceNotFoundException {
        try {
            service.get(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Language not found for this id : " + id);
        }
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

