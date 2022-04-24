package com.best.kindergarden.model.domain;
import com.best.kindergarden.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();  // yaratilgan sana

    private LocalDateTime lastModifiedDate = LocalDateTime.now();  // tahrirlangan sana

    @Enumerated(EnumType.STRING)
    private Status status;    //  Active, Deleted


}
