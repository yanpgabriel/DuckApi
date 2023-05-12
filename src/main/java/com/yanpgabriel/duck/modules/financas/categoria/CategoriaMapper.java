package com.yanpgabriel.duck.modules.financas.categoria;

import com.yanpgabriel.duck.modules.financas.conta.ContaMapper;
import com.yanpgabriel.duck.modules.financas.transacao.TransacaoMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {TransacaoMapper.class, ContaMapper.class}
)
public interface CategoriaMapper {

    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "idCategoriaPai", source = "categoriaPai.id")
    CategoriaDTO toCategoriaDTO(CategoriaEntity categoriaEntity);
    CategoriaEntity toCategoriaEntity(CategoriaDTO categoriaDTO);

}
