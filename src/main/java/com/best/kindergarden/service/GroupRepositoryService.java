package com.best.kindergarden.service;
import com.best.kindergarden.model.dto.GroupDTO;
import com.best.kindergarden.model.enums.Status;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public interface GroupRepositoryService {
     PageImpl<GroupDTO> findAll(Status status, String name, Integer maxChild, Integer page, Integer size);
}
