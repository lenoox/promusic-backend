package com.lenoox.promusic.orders.mapper;

import com.lenoox.promusic.orders.StatusParam;
import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.model.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {

    public Status paramToEntity(StatusParam statusParam) {
        Status status = new Status();
        status.setName(statusParam.getName());
        return status;
    }

    public StatusDto entityToDto(Status status) {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setName(status.getName());
        return statusDto;
    }
}
