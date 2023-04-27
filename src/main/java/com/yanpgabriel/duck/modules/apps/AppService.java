package com.yanpgabriel.duck.modules.apps;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AppService {
    
    @Inject
    AppMapper appMapper;
    
    @Transactional
    public AppEntity create(AppEntity appEntity) {
        appEntity.persist();
        return appEntity;
    }
    
    public AppDTO get(Long idApp) {
        return appMapper.toAppDTO(AppEntity.findById(idApp));
    }

    public List<AppDTO> list(Page page, Sort sort) {
        return AppEntity.findAll(sort).page(page).list().stream().map(app -> appMapper.toAppDTO((AppEntity) app)).collect(Collectors.toList());
    }
}
