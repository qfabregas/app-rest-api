package com.avoris.restapi.service;

import com.avoris.restapi.model.Language;
import com.avoris.restapi.repository.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LanguageService {

    @Autowired
    private LanguageRepo repo;

    public List<Language> listAll(){
        return repo.findAll();
    }

    public Language save(Language value) {
        return repo.save(value);
    }

    public Language get(int value) {
        return repo.findById(value).get();
    }

    public void delete(int value) {
        repo.deleteById(value);
    }

}
