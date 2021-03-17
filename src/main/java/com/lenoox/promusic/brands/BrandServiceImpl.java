package com.lenoox.promusic.brands;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service(value = "brandService")
public class BrandServiceImpl implements BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BrandDto> getAll(Pageable paging) {
        return brandRepository
                .findAll(paging)
                .stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public BrandDto getById(Long id) {
        Brand brand = brandRepository.findById(id).get();
        BrandDto brandDto = new BrandDto();
        modelMapper.map(brand, brandDto);
        return brandDto;
    }

    @Override
    public BrandDto create(BrandParam brandParam) {
        Brand brand = new Brand();
        modelMapper.map(brandParam, brand);
        brandRepository.save(brand);
        BrandDto brandDto = new BrandDto();
        modelMapper.map(brand, brandDto);
        return brandDto;
    }
    @Override
    public BrandDto update(Long id,BrandParam brandParam) {
        Brand brand= brandRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

        modelMapper.map(brandParam, brand);
        brandRepository.save(brand);
        BrandDto brandDto = new BrandDto();
        modelMapper.map(brand, brandDto);
        return brandDto;
    }
    @Override
    public void delete(long id) {
        brandRepository.deleteById(id);
    }
}