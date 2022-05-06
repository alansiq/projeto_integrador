package com.mercadolivre.bootcamp.projeto_integrador.dto.buyer;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewBuyerDTO {
    private String fullName;
    private String cep;

    public static Buyer convert(NewBuyerDTO newBuyerDTO) {
        return Buyer.builder().fullName(newBuyerDTO.getFullName()).build();
    }

    public static NewBuyerDTO convert(Buyer buyer) {
        return NewBuyerDTO.builder().fullName(buyer.getFullName()).build();
    }

    public Buyer map() {
        return Buyer.builder().fullName(this.getFullName()).build();
    }

    public static NewBuyerDTO map(Buyer buyer) {
        return NewBuyerDTO.builder().fullName(buyer.getFullName()).build();
    }

    public static List<NewBuyerDTO> map(List<Buyer> buyerList) {
        return buyerList.stream().map(NewBuyerDTO::map).collect(Collectors.toList());
    }
}
