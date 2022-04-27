package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long inBoundOrderNumber;
    private LocalDateTime inBoundOrderDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatchStock batchStock;
    //Todo: JoinColumn @ManyToOne
    private Long representativeId;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}
