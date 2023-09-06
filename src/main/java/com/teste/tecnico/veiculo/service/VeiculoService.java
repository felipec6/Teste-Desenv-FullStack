package com.teste.tecnico.veiculo.service;

import com.teste.tecnico.veiculo.dto.EditarVeiculoDTO;
import com.teste.tecnico.veiculo.dto.FilterVeiculoDTO;
import com.teste.tecnico.veiculo.dto.VeiculoDTO;
import com.teste.tecnico.veiculo.entity.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VeiculoService {
    public Page<Veiculo> encontrarPorFiltro(Pageable pageable, FilterVeiculoDTO filterVeiculo);

    ResponseEntity<?> criarVeiculo(VeiculoDTO veiculo);

    ResponseEntity<?> alterarVeiculo(Long id, EditarVeiculoDTO veiculo);

    ResponseEntity<?> deleteVeiculo(Long id);
}
