package com.avoris.restapi.repository;

import com.avoris.restapi.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language, Integer> {
}
