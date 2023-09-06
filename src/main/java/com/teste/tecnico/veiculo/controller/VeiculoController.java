package com.teste.tecnico.veiculo.controller;

import com.teste.tecnico.veiculo.assembler.VeiculoAssembler;
import com.teste.tecnico.veiculo.dto.EditarVeiculoDTO;
import com.teste.tecnico.veiculo.dto.FilterVeiculoDTO;
import com.teste.tecnico.veiculo.dto.VeiculoDTO;
import com.teste.tecnico.veiculo.entity.Veiculo;
import com.teste.tecnico.veiculo.service.VeiculoService;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    private final VeiculoAssembler veiculoAssembler;

    private final PagedResourcesAssembler<Veiculo> pagedResourcesAssembler;

    public VeiculoController(VeiculoService veiculoService,
                             VeiculoAssembler veiculoAssembler,
                             PagedResourcesAssembler<Veiculo> pagedResourcesAssembler) {
        this.veiculoService = veiculoService;
        this.veiculoAssembler = veiculoAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
    public PagedModel<EntityModel<VeiculoDTO>> encontrarTodosVeiculos(
            @PageableDefault(direction = Sort.Direction.DESC, size = 5) Pageable pageable
    ) {
        return encontrarPorFiltro(null, null, null, null, null, null, pageable);
    }


    @GetMapping("/filtro")
    @PageableAsQueryParam
    public PagedModel<EntityModel<VeiculoDTO>> encontrarPorFiltro(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "marca", required = false) String marca,
            @RequestParam(value = "ano", required = false) Integer ano,
            @RequestParam(value = "vendido", required = false) Boolean vendido,
            @RequestParam(value = "chassi", required = false) String chassi,
            @PageableDefault(direction = Sort.Direction.DESC, size = 5) Pageable pageable
    ) {
        PagedModel<EntityModel<Veiculo>> veiculoResult =
                pagedResourcesAssembler.toModel(veiculoService.encontrarPorFiltro(
                        pageable,
                        FilterVeiculoDTO.builder()
                                .id(id)
                                .nome(nome)
                                .marca(marca)
                                .ano(ano)
                                .vendido(vendido)
                                .chassi(chassi)
                                .build())
                );
        return PagedModel.of(veiculoResult.getContent().stream().map(e ->
                veiculoAssembler.toModel(e.getContent())).collect(Collectors.toList()), veiculoResult.getMetadata());
    }

    @GetMapping("/{id}")
    public PagedModel<EntityModel<VeiculoDTO>> encontrarVeiculoPorId(@PathVariable("id") Long id
                                                                     ) {
        return encontrarPorFiltro(id, null, null, null, null, null, null);
    }

    @PostMapping
    public ResponseEntity<?> criarVeiculo(@RequestBody VeiculoDTO veiculo) {
        return veiculoService.criarVeiculo(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarVeiculo(@PathVariable("id") Long id, @RequestBody EditarVeiculoDTO editarVeiculo) {
        return veiculoService.alterarVeiculo(id, editarVeiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVeiculo(@PathVariable("id") Long id) {
        return veiculoService.deleteVeiculo(id);
    }

}
