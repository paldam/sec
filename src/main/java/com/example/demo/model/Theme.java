package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String theme_uri;
    public String theme_name;
    public String author;
    public String author_uri;
    public String version;
    public String license;
    public String license_uri;
    public String template;
    public String description;
    public String tags;
}
