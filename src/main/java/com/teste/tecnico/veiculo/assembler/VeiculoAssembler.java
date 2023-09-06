package com.teste.tecnico.veiculo.assembler;

import com.teste.tecnico.veiculo.dto.VeiculoDTO;
import com.teste.tecnico.veiculo.entity.Veiculo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VeiculoAssembler implements RepresentationModelAssembler<Veiculo, EntityModel<VeiculoDTO>> {
    @Override
    public EntityModel<VeiculoDTO> toModel(Veiculo entity) {
        return EntityModel.of(VeiculoDTO.builder()
                .marca(entity.getMarca())
                .nome(entity.getNome())
                .ano(entity.getAno())
                .chassi(entity.getChassi())
                .created(entity.getCreated())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .updated(entity.getUpdated())
                .vendido(entity.getVendido())
                .build());
    }
}
