package com.avoris.restapi.service;

import com.avoris.restapi.model.Parameter;
import com.avoris.restapi.repository.ParameterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParameterService {

    @Autowired
    private ParameterRepo repo;

    public List<Parameter> listAll(){
        return repo.findAll();
    }

    public Parameter save(Parameter value) {
        return repo.save(value);
    }

    public Parameter get(String value) {
        return repo.findById(value).get();
    }

    public void delete(String value) {
        repo.deleteById(value);
    }

}
