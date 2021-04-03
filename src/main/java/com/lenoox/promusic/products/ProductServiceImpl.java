package com.lenoox.promusic.products;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
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
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EntityManager em;

    @Override
    public List<ProductDto> getAll(Pageable paging) {
        return productRepository
                .findAll(paging)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).get();
        ProductDto productDto = new ProductDto();
        modelMapper.map(product, productDto);
        return productDto;
    }

    @Override
    public ProductDto create(ProductParam productParam){
        Product product = new Product();
        modelMapper.map(productParam, product);
        Product productSaved = productRepository.save(product);
        em.refresh(productSaved);
        ProductDto productDto = new ProductDto();
        modelMapper.map(productSaved, productDto);
        return productDto;
    }

    @Override
    public ProductDto update(Long id,ProductParam productParam) {
        Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
        modelMapper.map(productParam, product);
        productRepository.save(product);
        ProductDto productDto = new ProductDto();
        modelMapper.map(product, productDto);
        return productDto;
    }
    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}