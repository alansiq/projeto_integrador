package com.mercadolivre.bootcamp.projeto_integrador.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salesman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
}
