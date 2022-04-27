package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;

import java.util.List;

public interface SalesmanService {
        Salesman createSalesman(Salesman salesman);
        Salesman findSalesmanById(Long salesmanId);
        List<Salesman> listSalesman();
        public Salesman updateSalesman(Salesman salesman);
        void removeSalesman(Long salesmanId);
}
