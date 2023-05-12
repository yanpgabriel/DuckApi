package com.yanpgabriel.duck.modules.financas.categoria;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaService {

    @Inject
    CategoriaMapper mapper;

    public List<CategoriaDTO> obterTodas() {
        return CategoriaEntity.listAll().stream().map(categoria -> mapper.toCategoriaDTO((CategoriaEntity) categoria)).collect(Collectors.toList());
    }

    public List<CategoriaDTO> obterTodasDeConta() {
        return CategoriaEntity.list("tipo = ?1", TipoCategoriaEnum.CONTA).stream().map(categoria -> mapper.toCategoriaDTO((CategoriaEntity) categoria)).collect(Collectors.toList());
    }

    public List<CategoriaDTO> obterTodasDeReceita() {
        return CategoriaEntity.list("tipo = ?1", TipoCategoriaEnum.RECEITA).stream().map(categoria -> mapper.toCategoriaDTO((CategoriaEntity) categoria)).collect(Collectors.toList());
    }

    public List<CategoriaDTO> obterTodasDeDespesa() {
        return CategoriaEntity.list("tipo = ?1", TipoCategoriaEnum.DESPESA).stream().map(categoria -> mapper.toCategoriaDTO((CategoriaEntity) categoria)).collect(Collectors.toList());
    }

    public List<CategoriaDTO> obterTodasDeTransacao() {
        return CategoriaEntity.list("tipo in (?1, ?2)", TipoCategoriaEnum.RECEITA, TipoCategoriaEnum.DESPESA).stream().map(categoria -> mapper.toCategoriaDTO((CategoriaEntity) categoria)).collect(Collectors.toList());
    }

}
