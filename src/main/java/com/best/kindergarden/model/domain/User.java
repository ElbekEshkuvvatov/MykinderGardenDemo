package com.best.kindergarden.model.domain;
import com.best.kindergarden.model.dto.UserDTO;
import com.best.kindergarden.model.enums.UserRole;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends  Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToMany
    private List<KinderGarden> kinderGardensIds;

    @ManyToMany
    private List<Group> groupsIds;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    public UserDTO map2DTO() {
        UserDTO userDTO = new UserDTO();

        List<Integer> kinderGardenIds = new ArrayList<>();
        for (KinderGarden kinderGarden : kinderGardensIds) {
            kinderGardenIds.add(kinderGarden.getId());
        }

        List<Integer> groupsId = new ArrayList<>();
        for (Group group : groupsIds) {
            groupsId.add(group.getId());
        }

        userDTO.setId(this.getId());
        userDTO.setFirstname(this.getFirstname());
        userDTO.setLastname(this.getLastname());
        userDTO.setPhoneNumber(this.getPhoneNumber());
        userDTO.setRoleName(this.getRoleName());
        userDTO.setKinderGardensIds(kinderGardenIds);
        userDTO.setGroupsIds(groupsId);

        userDTO.setCreatedDate(this.getCreatedDate());
        userDTO.setLastModifiedDate(this.getLastModifiedDate());
        return userDTO;
    }

}
