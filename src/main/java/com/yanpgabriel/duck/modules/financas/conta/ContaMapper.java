package com.yanpgabriel.duck.modules.financas.conta;

import com.yanpgabriel.duck.modules.financas.categoria.CategoriaMapper;
import com.yanpgabriel.duck.modules.financas.transacao.TransacaoMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CategoriaMapper.class, TransacaoMapper.class}
)
public interface ContaMapper {

    ContaMapper INSTANCE = Mappers.getMapper( ContaMapper.class );

    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "idCategoria", source = "categoria.id")
    ContaDTO toContaDTO(ContaEntity contaEntity);
    ContaEntity toContaEntity(ContaDTO contaDTO);

    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "idCategoria", source = "categoria.id")
    ContaSaldoDTO toSaldoDTO(ContaEntity contaEntity);

}
