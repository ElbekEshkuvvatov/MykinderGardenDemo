package com.best.kindergarden.service.impl;
import com.best.kindergarden.exceptions.ResourceNotFoundException;
import com.best.kindergarden.model.domain.Group;
import com.best.kindergarden.model.dto.GroupDTO;
import com.best.kindergarden.model.enums.Status;
import com.best.kindergarden.repository.GroupRepository;
import com.best.kindergarden.service.GroupRepositoryService;
import com.best.kindergarden.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;



@Service
@Log4j2
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

  private   final GroupRepository groupRepository;
  private   final GroupRepositoryService groupRepositoryService;


    @Override
    public GroupDTO save(GroupDTO groupDTO) {
        Group group = groupDTO.map2Entity();
        group.setStatus(Status.ACTIVE);
        groupRepository.save(group);
        log.debug("Group is saved groupEntity:{}", group);
        return group.map2DTO();
    }


    @Override
    public GroupDTO edit(Integer id, GroupDTO groupDTO) {
        Group group = groupRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("such group id was not found by id: " + id));
        group.setName(groupDTO.getName());
        group.setMaxChild(groupDTO.getMaxChild());
        groupRepository.save(group);
        log.debug("Response:{}", group);
        return group.map2DTO();
    }

    @Override
    public PageImpl<GroupDTO>  findAll(String name, Integer maxChild, Integer page, Integer size) {
        PageImpl<GroupDTO> response = groupRepositoryService.findAll(Status.ACTIVE, name, maxChild, page, size);
        log.debug("Response:{}", response);
        return response;
    }

    @Override
    public GroupDTO findById(Integer id) {
        Group group = groupRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("such group id was not found by id: " + id));
        log.debug("Response : {}", group);
        return group.map2DTO();

    }

    @Override
    public void delete(Integer id) {
        Group group = groupRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("such id group was not found by id= " + id));
        group.setStatus(Status.DELETED);
        log.debug("The Group deleted {}", group);
        groupRepository.save(group);
    }

}
