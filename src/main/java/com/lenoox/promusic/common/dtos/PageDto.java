package com.lenoox.promusic.common.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PageDto<T> {
     private List<T> content;
     private long totalElements;

     public PageDto(List<T> content, long totalElements) {
          this.content = content;
          this.totalElements = totalElements;
     }
}
