package com.avoris.restapi.controller;

import com.avoris.restapi.exception.ResourceNotFoundException;
import com.avoris.restapi.model.Parameter;
import com.avoris.restapi.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app-rest-api/v1")
public class ParameterController extends BaseController{

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParameterService service;

    // RESTfull API methods for Retrieval operations
    @GetMapping("/parameters")
    public List<Parameter> list() {
        return service.listAll();
    }

    @GetMapping("/parameters/report/{type}")
    public void list(HttpServletResponse response, @PathVariable String type) throws ResourceNotFoundException {
        log.info("Begin - Preparing the pdf report via jasper. (listPdf)");
        List<Parameter> parameter = service.listAll();
        // Adding the additional parameters to the pdf.
        final Map<String, Object> parametersAddtional = new HashMap<>();
        parametersAddtional.put("createdBy", "jfabregas");
        createReport(response, parameter, parametersAddtional, "report.jrxml", "report", type);
        log.info("End - Preparing the pdf report via jasper. (listPdf)");
    }

    /*
    @GetMapping("/parameters/{code}")
    public ResponseEntity<Parameter> get(@PathVariable String code) {
        try {
            Parameter parametro = service.get(code);
            return new ResponseEntity<Parameter>(parametro, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Parameter>(HttpStatus.NOT_FOUND);
        }
    }*/
    @GetMapping("/parameters/{code}")
    public ResponseEntity<Parameter> get(@PathVariable String code) throws ResourceNotFoundException {
        try {
            Parameter parametro = service.get(code);
            return new ResponseEntity<Parameter>(parametro, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Parameter not found for this id : " + code);
        }
    }

    // RESTfull API methods for Create operations
    @PostMapping("/parameters")
    public Parameter add(@RequestBody Parameter value) throws ResourceNotFoundException {
        try {
            return service.save(value);
        } catch(Exception e) {
            throw new ResourceNotFoundException("Parameter error in operation post for this id : " + value.getCode());
        }
    }

    /*
    // RESTfull API methods for Update operations
    @PutMapping("/parameters/{code}")
    public ResponseEntity<?> update(@RequestBody Parameter value, @PathVariable String code) throws ResourceNotFoundException {
        try {
            Parameter existProduct = service.get(code);
            service.save(value);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    // RESTfull API methods for Update operations
    @PutMapping("/parameters/{code}")
    public Parameter update(@RequestBody Parameter value, @PathVariable String code) throws ResourceNotFoundException {
        try {
            service.get(code);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Parameter not found for this id : " + code);
        }

        if (!(value.getCode().equals(code))) {
            throw new ResourceNotFoundException("Parameter error in operation put for this id : " + value.getCode() + "/" + code);
        }

        try{
            Parameter parametro = service.save(value);
            return parametro;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Parameter not found for this id : " + code);
        }
    }

    /*
    // RESTfull API methods for Delete operations
    @DeleteMapping("/parameters/{code}")
    public void delete(@PathVariable String code) throws ResourceNotFoundException {
        service.delete(code);
    }
    */
    @DeleteMapping("/parameters/{code}")
    public Map<String, Boolean> delete(@PathVariable String code) throws ResourceNotFoundException {
        try {
            service.get(code);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Parameter not found for this id : " + code);
        }
        service.delete(code);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

