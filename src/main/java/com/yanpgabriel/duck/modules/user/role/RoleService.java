package com.yanpgabriel.duck.modules.user.role;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RoleService {

    @Inject
    RoleMapper roleMapper;

    public List<RoleDTO> list(Page page, Sort sort) {
        return RoleEntity.findAll(sort).page(page).list().stream().map(role -> roleMapper.toRoleDTO((RoleEntity) role)).collect(Collectors.toList());
    }

}
