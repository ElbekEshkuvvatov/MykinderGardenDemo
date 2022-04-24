package com.best.kindergarden.model.domain;

import com.best.kindergarden.model.dto.KinderGardenDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class KinderGarden extends Base {



    @Column(nullable = false, unique = true)
    private String name;

    public KinderGardenDTO map2DTO(){
        KinderGardenDTO kinderGardenDTO = new KinderGardenDTO();
        kinderGardenDTO.setId(super.getId());
        kinderGardenDTO.setName(this.getName());
        kinderGardenDTO.setCreatedDate(this.getCreatedDate());
        kinderGardenDTO.setLastModifiedDate(this.getLastModifiedDate());
        kinderGardenDTO.setStatus(this.getStatus());
        return kinderGardenDTO;

    }

}