package com.yanpgabriel.duck.modules.user.profile;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProfileService {

    @Inject
    ProfileMapper profileMapper;

    public List<ProfileDTO> list(Page page, Sort sort) {
        return ProfileEntity.findAll(sort).page(page).list().stream().map(profile -> profileMapper.toProfileDTO((ProfileEntity) profile)).collect(Collectors.toList());
    }

    public ProfileDTO getById(Long id) {
        return profileMapper.toProfileDTO(ProfileEntity.findById(id));
    }
}
