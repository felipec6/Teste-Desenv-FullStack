package com.teste.tecnico.veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrosDTO {
    private List<String> erros;
}
