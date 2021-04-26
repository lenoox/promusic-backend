package com.lenoox.promusic.cart;

import com.lenoox.promusic.productorders.ProductOrderDto;
import com.lenoox.promusic.productorders.ProductOrderParam;
import com.lenoox.promusic.products.Product;
import com.lenoox.promusic.products.ProductDto;
import com.lenoox.promusic.products.ProductMapper;
import com.lenoox.promusic.products.ProductRepository;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CartMapper {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final EntityManager entityManager;
    public CartMapper(ProductMapper productMapper, ProductRepository productRepository, EntityManager entityManager) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public CartDto entityToDto(CartParam cartParam) {
        CartDto cartDto = new CartDto();
        BigDecimal grandTotal = new BigDecimal(0);
        Set<ProductOrderDto> productOrderList = new HashSet<>();
        for(ProductOrderParam orderProductElement : cartParam.getProductOrder()){
            Optional<Product> product =  productRepository.findById(orderProductElement.getProduct().getId());
            if(product.isPresent()){
                BigDecimal b1 =  new BigDecimal(orderProductElement.getQuantity());
                BigDecimal b2 =  product.get().getPrice();
                BigDecimal grandCurrent = b1.multiply(b2);
                grandTotal = grandTotal.add(grandCurrent);
                ProductOrderDto productOrderDto = new ProductOrderDto();
                ProductDto productDto = productMapper.entityToDto(product.get());
                productOrderDto.setProduct(productDto);
                productOrderDto.setQuantity(orderProductElement.getQuantity());
                productOrderList.add(productOrderDto);
            }
        }
        cartDto.setGrandTotal(grandTotal);
        cartDto.setProductOrder(productOrderList);
        return cartDto;
    }
}
