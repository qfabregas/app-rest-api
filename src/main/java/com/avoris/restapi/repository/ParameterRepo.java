package com.avoris.restapi.repository;

import com.avoris.restapi.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepo extends JpaRepository<Parameter, String> {
}
