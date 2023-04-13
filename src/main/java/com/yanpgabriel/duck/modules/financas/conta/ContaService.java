package com.yanpgabriel.duck.modules.financas.conta;

import com.yanpgabriel.duck.modules.user.UserDTO;
import com.yanpgabriel.duck.modules.user.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ContaService {

    @Inject
    ContaMapper mapper;

    @Inject
    UserService userService;

    public List<ContaDTO> obterTodos() {
        return ContaEntity.listAll().stream().map(conta -> mapper.toContaDTO((ContaEntity) conta)).collect(Collectors.toList());
    }

    public List<ContaSaldoDTO> obterSaldos() {
        UserDTO userDTO = userService.obterUsuarioLogado();
        List<ContaSaldoDTO> result = ContaEntity.getEntityManager()
                .createQuery(
                        "SELECT new com.yanpgabriel.duck.modules.financas.conta.ContaSaldoDTO(c.saldoInicial + COALESCE(SUM(t.valor), 0.00), c) FROM tb_conta c " +
                        "LEFT JOIN tb_transacao t ON t.conta = c AND t.efetivado IS TRUE " +
                        "WHERE c.usuario.id = :idUsuario " +
                        "GROUP BY c " +
                        "ORDER BY c.nome ASC", ContaSaldoDTO.class)
                .setParameter("idUsuario", userDTO.getId())
                .getResultList();
        return result;
    }

    public BigDecimal obterSaldoTotal() {
        return this.obterSaldos().stream()
                .filter(ContaDTO::getSomarNaTelaInicial)
                .map(ContaSaldoDTO::getSaldoAtual)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}