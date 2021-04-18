package com.lenoox.promusic.brands;

import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand paramToEntity(BrandParam brandParam) {
        Brand brand = new Brand();
        brand.setName(brandParam.getName());
        return brand;
    }

    public BrandDto entityToDto(Brand brand) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setName(brand.getName());
        return brandDto;
    }
}
