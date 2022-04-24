package com.best.kindergarden.service.impl;
import com.best.kindergarden.exceptions.ResourceNotFoundException;
import com.best.kindergarden.model.domain.KinderGarden;
import com.best.kindergarden.model.dto.KinderGardenDTO;
import com.best.kindergarden.model.enums.Status;
import com.best.kindergarden.repository.KinderGardenRepository;
import com.best.kindergarden.service.KinderGardenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KinderGardenServiceImpl implements KinderGardenService {

    final KinderGardenRepository kinderGardenRepository;
    private final KinderGardenRepositoryServiceImpl kinderGardenCustomRepository;


    @Override
    public KinderGardenDTO save(KinderGardenDTO kinderGardenDTO) {
        KinderGarden kinderGarden = kinderGardenDTO.map2Entity();
        kinderGarden.setStatus(Status.ACTIVE);
        kinderGardenRepository.save(kinderGarden);
        log.debug("KinderGarden is saved groupEntity:{}", kinderGarden);
        return kinderGarden.map2DTO();
    }

    @Override
    public KinderGardenDTO edit(Integer id, KinderGardenDTO kinderGardenDTO) {
        KinderGarden kinderGarden = kinderGardenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("such kinderGarden  id was not found "));
        kinderGarden.setName(kinderGardenDTO.getName());
        kinderGardenRepository.save(kinderGarden);
        log.debug("Response:{}", kinderGarden);
        return kinderGarden.map2DTO();
    }

    @Override
    public PageImpl<KinderGardenDTO> findAll(String name, Integer page, Integer size) {
        PageImpl<KinderGardenDTO> response = kinderGardenCustomRepository.findAll(Status.ACTIVE, name, page, size);
        log.debug("Response:{}", response);
        return response;

    }

    @Override
    public KinderGardenDTO findById(Integer id) {
        KinderGarden kinderGarden = kinderGardenRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("such kinderGarden  id was not found "));
        log.debug("Response : {}", kinderGarden);
        return kinderGarden.map2DTO();
    }

    @Override
    public void delete(Integer id) {
        KinderGarden kindergarden = kinderGardenRepository.findByIdAndStatus(id, Status.ACTIVE).
                orElseThrow(() -> new ResourceNotFoundException("Such kinderGarden  id was not found"));
        kindergarden.setStatus(Status.DELETED);
        log.debug("The Group deleted {}", kindergarden);
        kinderGardenRepository.save(kindergarden);
    }


}
