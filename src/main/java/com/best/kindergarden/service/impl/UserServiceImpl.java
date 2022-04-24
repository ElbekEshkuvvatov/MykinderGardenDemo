package com.best.kindergarden.service.impl;
import com.best.kindergarden.exceptions.ResourceNotFoundException;
import com.best.kindergarden.model.dto.UserDTO;
import com.best.kindergarden.model.enums.Status;
import com.best.kindergarden.repository.GroupRepository;
import com.best.kindergarden.repository.KinderGardenRepository;
import com.best.kindergarden.repository.UserRepository;
import com.best.kindergarden.model.domain.Group;
import com.best.kindergarden.model.domain.KinderGarden;
import com.best.kindergarden.model.domain.User;
import com.best.kindergarden.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final KinderGardenRepository kinderGardensRepository;
    final GroupRepository groupsRepository;
    private final UserRepositoryServiceImpl userCustomRepository;



    public UserServiceImpl(UserRepository userRepository, KinderGardenRepository kinderGardensRepository, GroupRepository groupsRepository, UserRepositoryServiceImpl userCustomRepository) {
        this.userRepository = userRepository;
        this.kinderGardensRepository = kinderGardensRepository;
        this.groupsRepository = groupsRepository;
        this.userCustomRepository = userCustomRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {

        List<KinderGarden> kinderGardensList = new ArrayList<>();

      List<Integer> kinderGardensIdList = userDTO.getKinderGardensIds();
      for (Integer kindID : kinderGardensIdList) {
          Optional<KinderGarden> optional = kinderGardensRepository.findById(kindID);
          optional.ifPresent(kinderGardensList::add);
      }

        List<Group> groupsList = new ArrayList<>();

       List<Integer> groupsIdList = userDTO.getGroupsIds();
       for (Integer groupId : groupsIdList) {
           Optional<Group> optional = groupsRepository.findById(groupId);
           optional.ifPresent(groupsList::add);
       }

        User user = userDTO.map2Entity(kinderGardensList, groupsList);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return user.map2DTO();

    }

    @Override
    public UserDTO edit(Integer id, UserDTO userDTO) {

        User user1 = userRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("Sorry! such user id was not found"));
        List<KinderGarden> kinderGardensList = new ArrayList<>();
        List<Integer> kinderGardensIdList = userDTO.getKinderGardensIds();
        for (Integer kindID : kinderGardensIdList) {
            Optional<KinderGarden> optional1 = kinderGardensRepository.findById(kindID);
            optional1.ifPresent(kinderGardensList::add);
        }

      List<Group> groupsList = new ArrayList<>();

        List<Integer> groupsIdList = userDTO.getGroupsIds();
        for (Integer groupId : groupsIdList) {
            Optional<Group> optional1 = groupsRepository.findById(groupId);
            optional1.ifPresent(groupsList::add);
        }

        user1.setFirstname(userDTO.getFirstname());
        user1.setLastname(userDTO.getLastname());
        user1.setPhoneNumber(userDTO.getPhoneNumber());
        user1.setRoleName(userDTO.getRoleName());
        user1.setKinderGardensIds(kinderGardensList);
        user1.setGroupsIds(groupsList);
        userRepository.save(user1);
        log.debug("Response:{}", user1);
        return user1.map2DTO();
    }

    @Override
    public PageImpl<UserDTO> findAll(String firstname, String lastname, Integer page, Integer size) {
        PageImpl<UserDTO> response = userCustomRepository.findAll(Status.ACTIVE, firstname, lastname, page, size);
        log.debug("Response:{}", response);
        return response;
    }

    @Override
    public UserDTO findById(Integer id) {
        User user = userRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() ->new ResourceNotFoundException("such user  id was not found"));
       log.debug("findById response :{} ", user);
        return user.map2DTO();
    }

    @Override
    public void delete(Integer id) {
            User user = userRepository.findByIdAndStatus(id, Status.ACTIVE)
                    .orElseThrow(() -> new ResourceNotFoundException("such user  id was not found"));
            user.setStatus(Status.DELETED);
        log.debug("The Group deleted {}", user);
            userRepository.save(user);
    }


}
