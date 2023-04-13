package com.yanpgabriel.duck.modules.financas.transacao;

import com.yanpgabriel.duck.modules.financas.categoria.CategoriaMapper;
import com.yanpgabriel.duck.modules.financas.conta.ContaMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CategoriaMapper.class, ContaMapper.class}
)
public interface TransacaoMapper {

    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "idConta", source = "conta.id")
    @Mapping(target = "idCategoria", source = "categoria.id")
    TransacaoDTO toTransacaoDTO(TransacaoEntity transacaoEntity);
    TransacaoEntity toTransacaoEntity(TransacaoDTO transacaoDTO);

}
