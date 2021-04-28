package com.lenoox.promusic.orders.service;

import com.lenoox.promusic.common.exception.ResourceNotFoundException;
import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.mapper.StatusMapper;
import com.lenoox.promusic.orders.model.Status;
import com.lenoox.promusic.orders.param.StatusParam;
import com.lenoox.promusic.orders.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(value = "statusService")
public class StatusServiceImpl implements StatusService {

    private static final Logger log = LoggerFactory.getLogger(StatusServiceImpl.class);
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;
    private final EntityManager em;

    public StatusServiceImpl(
            StatusRepository statusRepository,
            StatusMapper statusMapper,
            EntityManager em) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
        this.em = em;
    }
    @Override
    public List<StatusDto> getAll() {
        return statusRepository.findAll()
            .stream()
            .map(status -> statusMapper.entityToDto(status))
            .collect(Collectors.toList());
    }
    @Override
    public StatusDto getById(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        StatusDto statusDto = statusMapper.entityToDto(status);
        return statusDto;
    }
    @Override
    public StatusDto create(StatusParam statusParam) {
        Status status = statusMapper.paramToEntity(statusParam);
        Status statusSaved = statusRepository.save(status);
        em.refresh(statusSaved);
        StatusDto statusDto = statusMapper.entityToDto(statusSaved);
        return statusDto;
    }
    @Override
    public StatusDto update(Long id, StatusParam statusParam) {
        if (!statusRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        Status status = statusMapper.paramToEntity(statusParam);
        status.setId(id);
        Status statusSaved = statusRepository.save(status);
        StatusDto statusDto = statusMapper.entityToDto(statusSaved);
        return statusDto;
    }
    @Override
    public void delete(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        statusRepository.deleteById(id);
    }
}
