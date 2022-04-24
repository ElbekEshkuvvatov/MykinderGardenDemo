package com.best.kindergarden.model.dto;

import com.best.kindergarden.model.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GroupDTO {

    private Integer id;

    @NotEmpty(message = "Name cannot be null")
    private String name;

    @NotNull
    private Integer maxChild;

    private LocalDateTime createdDate = LocalDateTime.now();  // yaratilgan sana

    private LocalDateTime lastModifiedDate = LocalDateTime.now();  // tahrirlangan sana


    public Group map2Entity() {
        Group group = new Group();
        group.setName(this.getName());
        group.setMaxChild(this.getMaxChild());
        group.setCreatedDate(this.getCreatedDate());
        group.setLastModifiedDate(this.getLastModifiedDate());
        return group;
    }
}