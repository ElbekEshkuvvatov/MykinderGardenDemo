package com.best.kindergarden.model.dto;

import com.best.kindergarden.model.domain.KinderGarden;
import com.best.kindergarden.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class KinderGardenDTO {
    private  Integer id;

//    @NotNull(message = "Name cannot be null")   string "" kelishixam mumkun.
//    shuning uchun null giga ham pustoyligiga tekshirish kerak @NotEmpty()
    @NotEmpty(message = "Name cannot be null")
    private String name;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    private Status status;

    public KinderGarden map2Entity(){
        KinderGarden kinderGarden = new KinderGarden();

        kinderGarden.setName(this.getName());
        kinderGarden.setCreatedDate(this.getCreatedDate());
        kinderGarden.setLastModifiedDate(this.getLastModifiedDate());
        kinderGarden.setStatus(this.getStatus());
        return kinderGarden;
    }

}
