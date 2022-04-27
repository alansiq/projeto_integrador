package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inBoundOrderException.InBoundOrderEmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inBoundOrderException.InBoundOrderIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InBoundOrderServiceImpl implements InBoundOrderService{
    private final InBoundOrderRepository inBoundOrderrepository;

    @Override
    public InBoundOrder addInBoundOrder(NewInBoundOrderDTO inBoundOrderDTO){
        InBoundOrder inBoundOrder = InBoundOrder.builder()
                .inBoundOrderDate(LocalDateTime.now())
                .batchStock(inBoundOrderDTO.getBatchStock())
                .build();

        return inBoundOrderrepository.save(inBoundOrder);
    }

    @Override
    public List<InBoundOrder> getAllInBoundOrder(){
        return findAll();
    }

    @Override
    public InBoundOrder findById(Long inBoundOrderNumber){
        return inBoundOrderrepository.findById(inBoundOrderNumber).orElseThrow(() ->
                new InBoundOrderIdNotFoundException(inBoundOrderNumber));
    }

    @Override
    public List<InBoundOrder> findAll() {
        List<InBoundOrder> list = inBoundOrderrepository.findAll();
        if(list.isEmpty()) throw new EmptyListException();
        return list;
    }

    @Override
    public InBoundOrder updateInBoundOrder(InBoundOrder inBoundOrder){
        InBoundOrder updateInBoundOrderById = findById(inBoundOrder.getInBoundOrderNumber());

        updateInBoundOrderById.setInBoundOrderDate(inBoundOrder.getInBoundOrderDate());
        updateInBoundOrderById.setBatchStock(inBoundOrder.getBatchStock());

        return inBoundOrderrepository.save(updateInBoundOrderById);
    }

    @Override
    public void deleteInBoundOrder(Long inBoundOrderNumber){
        InBoundOrder inBoundOrder = findById(inBoundOrderNumber);
        inBoundOrderrepository.delete(inBoundOrder);
    }
}
