package com.lenoox.promusic.brands;

import com.lenoox.promusic.common.dtos.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    public static final String SUCCESS = "success";

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ApiResponse getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return new ApiResponse(HttpStatus.OK, SUCCESS, brandService.getAll(pageable));
    }
    @GetMapping(value = "/{id}")
    public ApiResponse getById(@PathVariable(value = "id") Long id){
        return new ApiResponse(HttpStatus.OK, SUCCESS, brandService.getById(id));
    }
    @PostMapping
    public ApiResponse create(@RequestBody BrandParam brand){
        return new ApiResponse(HttpStatus.OK, SUCCESS, brandService.create(brand));
    }
    @PutMapping(value = "/{id}")
    public ApiResponse update(@PathVariable(value = "id") Long id,
                              @RequestBody BrandParam brand){
        return new ApiResponse(HttpStatus.OK, SUCCESS, brandService.update(id,brand));
    }
    @DeleteMapping(value = "/{id}")
    public ApiResponse delete(@PathVariable(value = "id") Long id){
        brandService.delete(id);
        return new ApiResponse(HttpStatus.OK, SUCCESS, "deleted successfully");
    }
}