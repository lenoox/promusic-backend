package com.lenoox.promusic.products;

import com.lenoox.promusic.common.dtos.PageDto;
import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private EntityManager em;

    public PageDto<ProductDto> getByCategory(String category, Pageable paging) {

        List<Product> productList = em.createNativeQuery(
               " SELECT p.* FROM products p"+
               " INNER JOIN categories c ON p.category_id = c.category_id"+
               " WHERE c.slug = :category ORDER BY p.product_id ASC"
               ,Product.class)
                .setParameter("category", category)
                .setFirstResult((int) paging.getOffset())
                .setMaxResults(paging.getPageSize())
               .getResultList();
        BigInteger productListCount = (BigInteger) em.createNativeQuery(
                " SELECT COUNT(p.product_id) FROM products p"+
                        " INNER JOIN categories c ON p.category_id = c.category_id"+
                        " WHERE c.slug = :category")
                .setParameter("category", category)
                .getSingleResult();
        return new PageDto<>(productList
                .stream()
                .map(product -> productMapper.entityToDto(product))
                .collect(Collectors.toList()), Long.valueOf(productListCount.intValue()));
    }
    @Override
    public PageDto<ProductDto> getAll(Pageable paging) {
        Page<Product> productPage = productRepository.findAll(paging);
        return new PageDto(productPage.getContent()
                .stream()
                .map(product -> productMapper.entityToDto(product))
                .collect(Collectors.toList()), productPage.getTotalElements());
    }
    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        ProductDto productDto = productMapper.entityToDto(product);
        return productDto;
    }

    @Override
    public ProductDto create(ProductParam productParam){
        Product product = productMapper.paramToEntity(productParam);
        Product productSaved = productRepository.save(product);
        em.refresh(productSaved);
        ProductDto productDto = productMapper.entityToDto(productSaved);
        return productDto;
    }

    @Override
    public ProductDto update(Long id,ProductParam productParam) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        productParam.setId(id);
        Product product = productMapper.paramToEntity(productParam);

        productRepository.save(product);
        ProductDto productDto = productMapper.entityToDto(product);
        return productDto;
    }
    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
