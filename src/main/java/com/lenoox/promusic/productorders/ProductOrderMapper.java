package com.lenoox.promusic.productorders;

import com.lenoox.promusic.common.exception.OutOfStockException;
import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.orders.model.Order;
import com.lenoox.promusic.products.Product;
import com.lenoox.promusic.products.ProductDto;
import com.lenoox.promusic.products.ProductMapper;
import com.lenoox.promusic.products.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderMapper {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductOrderMapper(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }


    public ProductOrder paramToEntity(ProductOrderParam productOrderParam, Order orderSaved, Boolean updateQuantity) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrder(orderSaved);
        Product product = productRepository.findById(productOrderParam.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException(productOrderParam.getProduct().getId()));
        if(updateQuantity){
            int quantitAfterOrder = product.getQuantity()-productOrderParam.getQuantity();
            if(quantitAfterOrder >= 0){
                product.setQuantity(quantitAfterOrder);
            } else{
                throw new OutOfStockException(product.getBrand().getName() + " " +product.getName());
            }
        }
        productOrder.setProduct(product);
        productOrder.setQuantity(productOrderParam.getQuantity());
        return productOrder;
    }

    public ProductOrderDto entityToDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setId(productOrder.getId());
        ProductDto productDto = productMapper.entityToDto(productOrder.getProduct());
        productOrderDto.setProduct(productDto);
        productOrderDto.setQuantity(productOrder.getQuantity());
        return productOrderDto;
    }
}
