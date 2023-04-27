package com.yanpgabriel.duck.modules.financas.transacao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransacaoService {

    @Inject
    TransacaoMapper mapper;

    public List<TransacaoDTO> obterTodas() {
        return TransacaoEntity.listAll().stream().map(transacao -> mapper.toTransacaoDTO((TransacaoEntity) transacao)).collect(Collectors.toList());
    }

    public List<TransacaoDTO> obterTodasReceitas() {
        return TransacaoEntity.list("tipoTransacao = ?1", TipoTransacaoEnum.RECEITA).stream().map(transacao -> mapper.toTransacaoDTO((TransacaoEntity) transacao)).collect(Collectors.toList());
    }

    public List<TransacaoDTO> obterTodasDespesas() {
        return TransacaoEntity.list("tipoTransacao = ?1", TipoTransacaoEnum.DESPESA).stream().map(transacao -> mapper.toTransacaoDTO((TransacaoEntity) transacao)).collect(Collectors.toList());
    }

}
