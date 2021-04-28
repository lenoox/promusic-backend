package com.lenoox.promusic.orders.service;

import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.param.StatusParam;
import java.util.List;

public interface StatusService {
    List<StatusDto> getAll();
    StatusDto getById(Long id);
    StatusDto create(StatusParam status);
    StatusDto update(Long id, StatusParam status);
    void delete(Long id);
}
