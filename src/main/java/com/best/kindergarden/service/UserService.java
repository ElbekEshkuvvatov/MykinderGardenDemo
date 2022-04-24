package com.best.kindergarden.service;

import com.best.kindergarden.model.dto.UserDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

     UserDTO save(UserDTO userDTO);

    UserDTO edit(Integer id, UserDTO userDTO);

    PageImpl<UserDTO> findAll(String firstname, String lastname, Integer page, Integer size);

    UserDTO findById(Integer id);

    void  delete(Integer id);

}
