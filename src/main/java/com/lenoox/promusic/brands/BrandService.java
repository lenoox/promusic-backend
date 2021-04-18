package com.lenoox.promusic.brands;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    List<BrandDto> getAll(Pageable paging);
    BrandDto getById(Long id);
    BrandDto create(BrandParam brand);
    BrandDto update(Long id, BrandParam brand);
    void delete(Long id);
}
