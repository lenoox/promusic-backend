package com.lenoox.promusic.orders.mapper;

import com.lenoox.promusic.orders.param.StatusParam;
import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.model.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {

    public Status paramToEntity(StatusParam statusParam) {
        Status status = new Status();
        if(statusParam.getId() != null){
            status.setId(statusParam.getId());
        }
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
