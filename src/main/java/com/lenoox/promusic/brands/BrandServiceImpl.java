package com.lenoox.promusic.brands;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service(value = "brandService")
public class BrandServiceImpl implements BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private EntityManager em;

    @Override
    public List<BrandDto> getAll(Pageable paging) {
        return brandRepository
                .findAll(paging)
                .stream()
                .map(brand -> brandMapper.entityToDto(brand))
                .collect(Collectors.toList());
    }
    @Override
    public BrandDto getById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));;
        BrandDto brandDto = brandMapper.entityToDto(brand);
        return brandDto;
    }

    @Override
    public BrandDto create(BrandParam brandParam) {
        Brand brand = brandMapper.paramToEntity(brandParam);
        Brand brandSaved = brandRepository.save(brand);
        em.refresh(brandSaved);
        BrandDto brandDto = brandMapper.entityToDto(brand);
        return brandDto;
    }
    @Override
    public BrandDto update(Long id,BrandParam brandParam) {
        Brand brand = brandMapper.paramToEntity(brandParam);
        brand.setId(id);
        brandRepository.save(brand);
        BrandDto brandDto = brandMapper.entityToDto(brand);
        return brandDto;
    }
    @Override
    public void delete(long id) {
        brandRepository.deleteById(id);
    }
}
