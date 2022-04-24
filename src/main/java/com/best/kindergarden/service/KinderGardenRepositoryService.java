package com.best.kindergarden.service;
import com.best.kindergarden.model.dto.KinderGardenDTO;
import com.best.kindergarden.model.enums.Status;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public interface KinderGardenRepositoryService {
    PageImpl<KinderGardenDTO> findAll(Status status, String name, Integer page, Integer size);
}
