package com.avoris.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametro", schema = "proveedor")
public class Parameter {

    private String code;
    private String name;
    private String description;
    private String value;

    public Parameter(){}

    public Parameter(String codigo, String nom, String descripcion, String valor){
        this.code = codigo;
        this.name = nom;
        this.description = descripcion;
        this.value = valor;
    }

    public Parameter(String codigo, String valor){
        this.code = codigo;
        this.value = valor;
    }

    @Id
    @Column(name = "codigo")
    public String getCode() {
        return code;
    }
    public void setCode(String value) {
        this.code = value;
    }

    @Column(name = "nom")
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }

    @Column(name = "descripcion")
    public String getDescription() {
        return description;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    @Column(name = "valor")
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Parameter{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
