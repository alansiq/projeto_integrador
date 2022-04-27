package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSalesmanDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SalesmanDoesNotExistException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository salesmanRepository;

    @Override
    public Salesman createSalesman(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

    @Override
    public Salesman findSalesmanById(String salesmanId) {
        return salesmanRepository.findById(salesmanId).
                orElseThrow(() -> new SalesmanDoesNotExistException(salesmanId));
    }

    @Override
    public List<Salesman> listSalesman() {
        return salesmanRepository.findAll();
    }

    @Override
    public void removeSalesman(String salesmanId) {
        salesmanRepository.delete(findSalesmanById(salesmanId));
    }

    @Override
    public Salesman updateSalesman(String salesmanId, Salesman salesman){
        Salesman updatedSalesman = findSalesmanById(salesmanId);

        updatedSalesman.setFullName(salesman.getFullName());
        return salesmanRepository.save(updatedSalesman);
        }

}
