package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.buyer.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BuyerRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {
    @Mock
    private BuyerRepository BuyerRepository;

    @InjectMocks
    private BuyerServiceImpl BuyerService;

    NewBuyerDTO buyerDTO = NewBuyerDTO.builder().fullName("afonso").build();
    Buyer buyer = Buyer.builder().fullName("afonso").build();

    @Test
    void shouldCreateBuyer() {
        Buyer buyer = Buyer.builder().build();

        //Act
        Mockito.when(BuyerRepository.save(any())).thenReturn(buyer);

        Buyer buyerAdd =
                BuyerService.addBuyer(buyer);

        //Assert
        assertEquals(buyer, buyerAdd);
    }

    @Test
    void shouldFindBuyerById() {
        //Act
        Mockito.when(BuyerRepository.findById(any())).thenReturn(Optional.of(buyer));
        Buyer foundedBuyer = BuyerService.findById(1L);

        //Assert
        assertEquals(buyer, foundedBuyer);
    }

    @Test
    void shouldNotFindBuyerById() {
        //Act
        Mockito.when(BuyerRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> BuyerService.findById(1L)
        );
    }

    @Test
    void shouldReturnAllBuyer() {
        //Arrange
        Buyer buyer1 = Buyer.builder().build();
        Buyer buyer2 = Buyer.builder().build();
        Buyer buyer3 = Buyer.builder().build();

        List<Buyer> buyerList =
                Arrays.asList(buyer1, buyer2, buyer3);

        //Act
        Mockito.when(BuyerRepository.findAll()).thenReturn(buyerList);

        List<Buyer> buyerListTest =
                BuyerService.getAllBuyer();

        //Assert
        assertEquals(buyerList, buyerListTest);
    }

    @Test
    void shouldNotReturnAllBuyer() {
        //Arrange
        List<Buyer> buyerList = Arrays.asList();

        //Act
        Mockito.when(BuyerRepository.findAll()).thenReturn(buyerList);

        //Assert
        assertThrows(EmptyListException.class, ()
                -> BuyerService.getAllBuyer()
        );
    }

    @Test
    void shouldUpdateBuyer() {
        //Arrange
        Buyer buyer = Buyer.builder().build();
        Optional<Buyer> buyerOptional = Optional.of(buyer);


        Mockito.when(BuyerRepository.findById(any())).thenReturn(buyerOptional);
        Mockito.when(BuyerRepository.save(any())).thenReturn(buyer);
        Buyer updatedBuyer =
                BuyerService.updateBuyer(buyer);

        //Assert
        Assertions.assertEquals(buyerOptional.get(),updatedBuyer);
    }

    @Test
    void shouldDeleteBuyer() {
        //Arrange
        Buyer buyer = Buyer.builder().buyerId(1L).build();

        //Act
        Mockito.when(BuyerRepository.findById(any())).thenReturn(Optional.of(buyer));
        doNothing().when(BuyerRepository).delete(any());

        //Assert
        assertDoesNotThrow(() -> {
            BuyerService.deleteBuyer(1L);
        });
    }

    @Test
    void shouldNotDeleteBuyer() {
        //Act
        Mockito.when(BuyerRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> BuyerService.deleteBuyer(1L)
        );
    }
}
