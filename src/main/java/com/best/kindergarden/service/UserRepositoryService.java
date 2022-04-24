package com.best.kindergarden.service;

import com.best.kindergarden.model.dto.UserDTO;
import com.best.kindergarden.model.enums.Status;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public interface UserRepositoryService {
    PageImpl<UserDTO> findAll(Status status, String firstname, String lastname, Integer page, Integer size);
}
