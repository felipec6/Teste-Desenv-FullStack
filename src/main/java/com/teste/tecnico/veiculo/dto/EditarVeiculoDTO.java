package com.teste.tecnico.veiculo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class EditarVeiculoDTO {
    @NotNull
    @Size(max = 15)
    private String nome;
    @NotNull
    @Size(max = 15)
    private String marca;
    @NotNull
    @Size(max = 4)
    private Integer ano;
    @NotNull
    @Size(max = 55)
    private String descricao;
    @NotNull
    private Boolean vendido;
    @NotNull
    private Date updated;
    @NotNull
    @Size(max = 25)
    private String chassi;
    @NotNull
    @Size(max = 15)
    private BigDecimal preco;
}
