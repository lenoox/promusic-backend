package com.lenoox.promusic.categories;

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
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private EntityManager em;

    @Override
    public List<CategoryDto> getAll(Pageable paging) {
        return categoryRepository
                .findAll(paging)
                .stream()
                .map(category -> categoryMapper.entityToDto(category))
                .collect(Collectors.toList());
    }
    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        CategoryDto categoryDto = categoryMapper.entityToDto(category);
        return categoryDto;
    }

    @Override
    public CategoryDto create(CategoryParam categoryParam) {
        Category category = categoryMapper.paramToEntity(categoryParam);
        Category categorySaved = categoryRepository.save(category);
        em.refresh(categorySaved);
        CategoryDto categoryDto = categoryMapper.entityToDto(category);
        return categoryDto;
    }
    @Override
    public CategoryDto update(Long id,CategoryParam categoryParam) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        Category category = categoryMapper.paramToEntity(categoryParam);
        category.setId(id);
        categoryRepository.save(category);
        CategoryDto categoryDto = categoryMapper.entityToDto(category);
        return categoryDto;
    }
    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }
}
