package com.teste.tecnico.veiculo.entity;

import com.teste.tecnico.veiculo.dto.EditarVeiculoDTO;
import com.teste.tecnico.veiculo.dto.VeiculoDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "veiculo_table")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veiculo_id")
    private Long id;

    @Column(name = "veiculo_nome")
    private String nome;

    @Column(name = "veiculo_marca")
    private String marca;

    @Column(name = "veiculo_ano")
    private Integer ano;

    @Column(name = "veiculo_desc")
    private String descricao;

    @Column(name = "veiculo_vendido")
    private Boolean vendido;

    @Column(name = "veiculo_created")
    private Date created;

    @Column(name = "veiculo_updated")
    private Date updated;

    @Column(name = "veiculo_chassi")
    private String chassi;

    @Column(name = "veiculo_preco")
    private BigDecimal preco;

    public static Veiculo dtoToEntity(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome(dto.getNome());
        veiculo.setMarca(dto.getMarca());
        veiculo.setAno(dto.getAno());
        veiculo.setChassi(dto.getChassi());
        veiculo.setCreated(dto.getCreated());
        veiculo.setDescricao(dto.getDescricao());
        veiculo.setPreco(dto.getPreco());
        veiculo.setVendido(dto.getVendido());
        return veiculo;
    }

    public static Veiculo validarAlteracao(Veiculo veiculo, EditarVeiculoDTO edicao) {
        if(edicao.getNome() != null && !edicao.getNome().isEmpty()) {
            veiculo.setNome(edicao.getNome());
        }
        if(edicao.getMarca() != null && !edicao.getMarca().isEmpty()) {
            veiculo.setMarca(edicao.getMarca());
        }
        if(edicao.getAno() != null) {
            veiculo.setAno(edicao.getAno());
        }
        if(edicao.getDescricao() != null && !edicao.getDescricao().isEmpty()) {
            veiculo.setDescricao(edicao.getDescricao());
        }
        if(edicao.getVendido() != null) {
            veiculo.setVendido(edicao.getVendido());
        }
        if(edicao.getChassi() != null && !edicao.getChassi().isEmpty()) {
            veiculo.setChassi(edicao.getChassi());
        }
        if(edicao.getPreco() != null) {
            veiculo.setPreco(edicao.getPreco());
        }
        return veiculo;
    }
}
