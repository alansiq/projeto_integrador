package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SectionNotFound;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.SectionService;
import com.mercadolivre.bootcamp.projeto_integrador.service.SectionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private SectionServiceImpl sectionService;

    @Test
    void shouldSaveNewSection() {
        //Arrange
        NewSectionDTO sectionDTO = NewSectionDTO.builder()
                .capacity(new BigDecimal(100))
                .currentTemperature(10)
                .category(Category.REFRIGERATED)
                .warehouseId(5L).build();

        Section section = Section.builder().build();

        //Act

        Mockito.when(sectionRepository.save(any())).thenReturn(section);

        Section sectionAdd = sectionService.addSection(sectionDTO);

        //Assert
        Assertions.assertEquals(section, sectionAdd);

    }

    @Test
    void shouldReturnAllSections() {
        //Arrange
        Section section = Section.builder().build();
        Section section1 = Section.builder().build();
        Section section2 = Section.builder().build();

        List<Section> listSections = Arrays.asList(section,section2,section1);

        //Act
        Mockito.when(sectionRepository.findAll()).thenReturn(listSections);

        List<Section> listSectionsTest = sectionService.getAllSection();

        //Assert
        Assertions.assertEquals(listSections, listSectionsTest);

    }

    @Test
    void shouldReturnSectionById() {

        //Arrange
        Section section = Section.builder()
                .sectionId(1l)
                .capacity(new BigDecimal(100))
                .category(Category.REFRIGERATED)
                .currentTemperature(15)
                .warehouseId(1L)
                .listInBoundOrder(new ArrayList<>()).build();

        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Section foundSection = sectionService.getSectionById(any());

        //Assert
        Assertions.assertEquals(sectionOptional.get(), foundSection);

    }


    @Test
    void shouldUpdateSection(){
        //Arrange
        NewSectionDTO sectionDTO = NewSectionDTO.builder()
                .capacity(new BigDecimal(100))
                .currentTemperature(10)
                .category(Category.REFRIGERATED)
                .warehouseId(1L).build();

        Section section = Section.builder().build();
        Optional<Section> sectionOptional = Optional.of(section);


        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Mockito.when(sectionRepository.save(any())).thenReturn(section);
        Section sectionUpdated = sectionService.updateSection(section);

        //Assert

        Assertions.assertEquals(sectionOptional.get(), sectionUpdated);

    }


    @Test
    void shouldReturnErrorWhenFindById() {

        //Arrange
        Optional<Section> sectionOptionalNull = Optional.empty();

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptionalNull);

        //Assert
        Assertions.assertThrows(SectionNotFound.class,
                () -> {
                    sectionService.getSectionById(1000000L);
                });
    }
    @Test
    void shouldReturnErrorWhenUpdateSectionAndNotFoundId() {

        //Arrange
        Section section = Section.builder().build();
        Optional<Section> sectionOptionalNull = Optional.empty();

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptionalNull);

        //Assert
        Assertions.assertThrows(SectionNotFound.class,
                () -> {
                    sectionService.updateSection(section);
                });
    }

    @Test
    void shouldReturnTrueWhenCallMethodIsValidSection() {

        //Arrange
        Section section = Section.builder().build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        boolean isValid = sectionService.isSectionValid(5L);

        //Assert
        Assertions.assertTrue(isValid);

    }

    @Test
    void shouldReturnTrueWhenAvailableSectionHaveCapacity(){
        //Arrange
        Section section = Section.builder().capacity(new BigDecimal(10000)).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Boolean isCapacity = sectionService.availableSectionCapacity(new BigDecimal(200), any());

        //Asserts
        Assertions.assertTrue(isCapacity);

    }

    @Test
    void shouldReturnFalseWhenAvailableSectionDontHaveCapacity(){
        //Arrange
        Section section = Section.builder().capacity(new BigDecimal(10000)).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Boolean isCapacity = sectionService.availableSectionCapacity(new BigDecimal(12000), any());

        //Asserts
        Assertions.assertFalse(isCapacity);

    }

    @Test
    public void shouldDeleteSection() {
        // Arrange
        Section section = Section.builder().sectionId(100l).build();

        // Act
        doNothing().when(sectionRepository).deleteById(any());

        // Assert
        assertDoesNotThrow(() -> {
            sectionService.deleteSection(100L);
        });
    }

    @Test
    public void shouldReturnTrueWhenSectionCorrespondsProductType(){

        //Arrange
        Product product01 = Product.builder().category(Category.REFRIGERATED).build();
        BatchStock batchStock1 = BatchStock.builder().product(product01).build();
        Section section = Section.builder().category(Category.REFRIGERATED).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        boolean correspondsProductType = sectionService.sectionCorrespondsProductType(any(), batchStock1.getProduct().getCategory());

        //Asserts
        Assertions.assertTrue(correspondsProductType);

    }

    @Test
    public void shouldReturnFalseWhenSectionDontCorrespondsProductType(){
        //Arrange
        Product product01 = Product.builder().category(Category.REFRIGERATED).build();
        BatchStock batchStock1 = BatchStock.builder().product(product01).build();
        Section section = Section.builder().category(Category.FRESH).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        boolean correspondsProductType = sectionService.sectionCorrespondsProductType(any(), batchStock1.getProduct().getCategory());

        //Asserts
        Assertions.assertFalse(correspondsProductType);
    }

    @Test
    void shouldReturnErrorWhenVerifySectionCorrespondsProductTypeAndNotFoundId() {

        //Arrange
        Product product01 = Product.builder().category(Category.REFRIGERATED).build();
        BatchStock batchStock1 = BatchStock.builder().product(product01).build();
        Optional<Section> sectionOptionalNull = Optional.empty();


        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptionalNull);

        //Assert
        Assertions.assertThrows(SectionNotFound.class,
                () -> {
                    sectionService.sectionCorrespondsProductType(any(), batchStock1.getProduct().getCategory());
                });
    }



}
