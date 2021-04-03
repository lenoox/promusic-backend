package com.lenoox.promusic.categories;

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
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityManager em;

    @Override
    public List<CategoryDto> getAll(Pageable paging) {
        return categoryRepository
                .findAll(paging)
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = new CategoryDto();
        modelMapper.map(category, categoryDto);
        return categoryDto;
    }

    @Override
    public CategoryDto create(CategoryParam categoryParam) {
        Category category = new Category();
        modelMapper.map(categoryParam, category);
        Category categorySaved = categoryRepository.save(category);
        em.refresh(categorySaved);
        CategoryDto categoryDto = new CategoryDto();
        modelMapper.map(category, categoryDto);
        return categoryDto;
    }
    @Override
    public CategoryDto update(Long id,CategoryParam categoryParam) {
        Category category= categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

        modelMapper.map(categoryParam, category);
        categoryRepository.save(category);
        CategoryDto categoryDto = new CategoryDto();
        modelMapper.map(category, categoryDto);
        return categoryDto;
    }
    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}