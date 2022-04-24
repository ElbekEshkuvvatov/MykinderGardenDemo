package com.best.kindergarden.service;
import com.best.kindergarden.model.dto.GroupDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;



@Service
public interface GroupService {

    GroupDTO save(GroupDTO groupDTO);

    GroupDTO edit(Integer id, GroupDTO groupDTO);


    PageImpl<GroupDTO>  findAll(String name, Integer maxChild, Integer page, Integer size);

    GroupDTO findById(Integer id) ;

    void delete(Integer id);




}
