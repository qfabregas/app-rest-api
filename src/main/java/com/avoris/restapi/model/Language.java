package com.avoris.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "idiomes", schema = "sysdba")
public class Language {

    private int id_language;
    private String abbreviation;
    private String name;
    private String name2;
    private String name3;
    private String iso3;
    private String language;
    private int print;
    private String iso2;

    public Language(){}

    public Language(int id_language, String abbreviation, String name, String name2, String name3, String iso3, String language, int print, String iso2){
        this.id_language = id_language;
        this.abbreviation = abbreviation;
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.iso3 = iso3;
        this.language = language;
        this.print = print;
        this.iso2 = iso2;
    }

    @Id
    @Column(name = "idiomaid")
    public int getIdLanguage() {
        return id_language;
    }
    public void setIdLanguage(int value) {
        this.id_language = value;
    }

    @Column(name = "abr")
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Column(name = "nom")
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }

    @Column(name = "pdetavalor2")
    public String getName2() {
        return name2;
    }
    public void setName2(String value) {
        this.name2 = value;
    }

    @Column(name = "pdetavalor3")
    public String getName3() {
        return name3;
    }
    public void setName3(String value) {
        this.name3 = value;
    }

    @Column(name = "iso3")
    public String getIso3() {
        return iso3;
    }
    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "impresion")
    public int getPrint() {
        return print;
    }
    public void setPrint(int value) {
        this.print = value;
    }

    @Column(name = "iso2")
    public String getIso2() {
        return iso2;
    }
    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id_language=" + id_language +
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                ", name2='" + name2 + '\'' +
                ", name3='" + name3 + '\'' +
                ", iso3='" + iso3 + '\'' +
                ", language='" + language + '\'' +
                ", print=" + print +
                ", iso2='" + iso2 + '\'' +
                '}';
    }

}
