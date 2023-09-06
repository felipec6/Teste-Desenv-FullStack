package com.teste.tecnico.veiculo.repository;

import com.teste.tecnico.veiculo.entity.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query("SELECT V FROM Veiculo V WHERE" +
            " ((:id is null) or V.id = :id)" +
            " AND ((:nome is null) or V.nome = :nome)" +
            " AND ((:marca is null) or V.marca = :marca)" +
            " AND ((:ano is null) or V.ano = :ano)" +
            " AND ((:vendido is null) or V.vendido = :vendido)" +
            " AND ((:chassi is null) or V.chassi = :chassi)")
    Page<Veiculo> findAllVeiculosByFiltro(Pageable pageable, Long id, String nome, String marca, Integer ano, Boolean vendido, String chassi);

    Optional<Veiculo> findByChassi(String chassi);
}
