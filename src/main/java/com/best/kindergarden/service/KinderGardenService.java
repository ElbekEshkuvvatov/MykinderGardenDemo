package com.best.kindergarden.service;
import com.best.kindergarden.model.dto.KinderGardenDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;



@Service
public interface KinderGardenService {
    KinderGardenDTO save(KinderGardenDTO kinderGardenDTO);

    KinderGardenDTO edit(Integer id, KinderGardenDTO kinderGardenDTO);


    KinderGardenDTO findById(Integer id);

    PageImpl<KinderGardenDTO> findAll(String name, Integer page, Integer size );

    void delete(Integer id);


}
