package com.teste.tecnico.veiculo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FilterVeiculoDTO {
    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Boolean vendido;
    private String chassi;
}
