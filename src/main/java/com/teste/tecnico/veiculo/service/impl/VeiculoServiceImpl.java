package com.teste.tecnico.veiculo.service.impl;

import com.teste.tecnico.veiculo.dto.EditarVeiculoDTO;
import com.teste.tecnico.veiculo.dto.ErrosDTO;
import com.teste.tecnico.veiculo.dto.FilterVeiculoDTO;
import com.teste.tecnico.veiculo.dto.VeiculoDTO;
import com.teste.tecnico.veiculo.entity.Veiculo;
import com.teste.tecnico.veiculo.repository.VeiculoRepository;
import com.teste.tecnico.veiculo.service.VeiculoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.teste.tecnico.veiculo.entity.Veiculo.dtoToEntity;
import static com.teste.tecnico.veiculo.entity.Veiculo.validarAlteracao;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private static VeiculoRepository veiculoRepository;

    public VeiculoServiceImpl(
            VeiculoRepository veiculoRepository
    ) {
        VeiculoServiceImpl.veiculoRepository = veiculoRepository;
    }

    @Override
    public Page<Veiculo> encontrarPorFiltro(Pageable pageable, FilterVeiculoDTO filterVeiculo) {
        return veiculoRepository.findAllVeiculosByFiltro(
                pageable, filterVeiculo.getId(), filterVeiculo.getNome(), filterVeiculo.getMarca(), filterVeiculo.getAno(), filterVeiculo.getVendido(), filterVeiculo.getChassi());
    }

    @Override
    public ResponseEntity<?> criarVeiculo(VeiculoDTO veiculo) {
        List<String> erros = veiculoDadosValidos(veiculo);
        if(erros.isEmpty()) {
            veiculoRepository.save(dtoToEntity(veiculo));
            return ResponseEntity.ok(veiculo);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrosDTO(erros));
    }

    @Override
    public ResponseEntity<?> alterarVeiculo(Long id, EditarVeiculoDTO veiculo) {
        List<String> erros = new ArrayList<>();
        if(veiculo != null) {
            Optional<Veiculo> veiculoOptional = encontrarVeiculoPorId(id);
            if (veiculoOptional.isPresent()) {
                Veiculo veiculoEditado = veiculoOptional.get();
                veiculoEditado.setUpdated(new Date());
                Veiculo veiculoResult = veiculoRepository.save(validarAlteracao(veiculoEditado, veiculo));
                return ResponseEntity.ok(veiculoResult);
            } else {
                erros.add("Veículo não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrosDTO(erros));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrosDTO(erros));
    }

    @Override
    public ResponseEntity<?> deleteVeiculo(Long id) {
        Optional<Veiculo> veiculoOptional = encontrarVeiculoPorId(id);
        if(veiculoOptional.isPresent()) {
            veiculoRepository.delete(veiculoOptional.get());
            return ResponseEntity.ok(null);
        }
        List<String> erros = new ArrayList<>();
        erros.add("Veículo não encontrado.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    private Optional<Veiculo> encontrarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    private List<String> veiculoDadosValidos(VeiculoDTO veiculo) {
        List<String> erros = new ArrayList<>();
        if(!validacaoChassi(veiculo.getChassi()).isEmpty()) {
            erros.add("RN001 - Não pode haver veiculos com o mesmo numero de chassi.");
        }
        if(!(veiculo.getPreco().compareTo(BigDecimal.ZERO) > 0)) {
            erros.add("RN002 - O sistema não deve permite preços negativos.");
        }
        if(!validacaoAno(veiculo.getAno())) {
            erros.add("RN003 -  O sistema não deve permitir que veiculos tenham no cadastro ou atualização, " +
                    "ano supeior ao corrente, por exemplo: o ano atual é 2023 e um veiculo estão tentando cadastrar com o ano 2024.");
        }
        return erros;
    }

    private Boolean validacaoAno(Integer ano) {
        Calendar cal = Calendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);
        return ano <= anoAtual;
    }

    private Optional<Veiculo> validacaoChassi(String chassi) {
        return veiculoRepository.findByChassi(chassi);
    }
}
