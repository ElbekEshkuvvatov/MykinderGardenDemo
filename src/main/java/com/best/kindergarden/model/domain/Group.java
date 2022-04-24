package com.best.kindergarden.model.domain;


import com.best.kindergarden.model.dto.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group extends  Base {

    @Column(nullable = false)
    private  String name;

    private Integer maxChild;

    public GroupDTO map2DTO(){
      GroupDTO groupDTO = new GroupDTO();
      groupDTO.setId(super.getId());
      groupDTO.setName(this.getName());
      groupDTO.setMaxChild(this.getMaxChild());
      groupDTO.setCreatedDate(super.getCreatedDate());
      groupDTO.setLastModifiedDate(super.getLastModifiedDate());

      return groupDTO;
    }
}
