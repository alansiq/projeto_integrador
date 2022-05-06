package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This class represents the Representative controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping(RepresentativeController.baseUri)
public class RepresentativeController {

    //Requisito 06 - Marianna Rodrigues (Realizado no começo do projeto)

    private final RepresentativeService representativeService;

    public static final String baseUri =  "/api/v1/fresh-products/representative";

    /**
     *
     * @return 200 OK
     */
    @GetMapping("/list")
    public ResponseEntity<List<Representative>> getAllRepresentatives() {
        List<Representative> representativeList = representativeService.getAllRepresentatives();
        return ResponseEntity.ok().body(representativeList);
    }

    /**
     *
     * @param representativeId Id to get a representative
     * @return 200 OK
     */
    @GetMapping
    public ResponseEntity<Representative> getByRepresentativeId(@RequestParam(value = "id") Long representativeId) {
        Representative representative = representativeService.getRepresentativeById(representativeId);
        return ResponseEntity.ok().body(representative);
    }

    /**
     *
     * @param representative Representative object to persist in database.
     * @return 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Representative> createRepresentative(@Valid @RequestBody Representative representative) {
        Representative representativeAux = representativeService.createRepresentative(representative);
        return ResponseEntity.ok().body(representativeAux);
    }

    /**
     *
     * @param representative Representative object in request body to update a Representative by id
     * @return 200 OK
     */
    @PutMapping
    public ResponseEntity<Representative> updateRepresentative(@Valid @RequestBody Representative representative) {
        Representative representativeAux = representativeService.updateRepresentative(representative);
        return ResponseEntity.ok().body(representativeAux);
    }

    /**
     *
     * @param representativeId Id of to remove a representative of database.
     * @return 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRepresentative(@PathVariable(value = "id") Long representativeId) {
        representativeService.deleteRepresentative(representativeId);
        return ResponseEntity.ok().body("success");
    }
}
