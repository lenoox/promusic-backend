package com.lenoox.promusic.common.dtos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PageDto<T> {
     private List<T> content;
     private long totalElements;
     private int totalPages;

     public PageDto(List<T> order, long totalElements, int totalPages) {
          this.content = order;
          this.totalElements = totalElements;
          this.totalPages = totalPages;
     }
}
