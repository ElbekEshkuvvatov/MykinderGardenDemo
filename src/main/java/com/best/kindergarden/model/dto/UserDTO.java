package com.best.kindergarden.model.dto;
import com.best.kindergarden.model.domain.Group;
import com.best.kindergarden.model.domain.KinderGarden;
import com.best.kindergarden.model.domain.User;
import com.best.kindergarden.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private Integer id;

    @NotNull(message = "Name cannot be null")
    private String firstname;

    @NotNull(message = "LastName cannot be null")
    private String lastname;

    @NotNull(message = "phoneNumber cannot be null")
    @Size(max = 13)
    private String phoneNumber;

    @NotNull(message = "roleName cannot be null")
    private UserRole roleName;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime lastModifiedDate = LocalDateTime.now();


    private List<Integer> kinderGardensIds;
    private List<Integer> groupsIds;

    public User map2Entity(List<KinderGarden> kinderGardens, List<Group> groupsList) {
        User user = new User();
        user.setFirstname(this.getFirstname());
        user.setLastname(this.getLastname());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setRoleName(this.getRoleName());
        user.setCreatedDate(this.getCreatedDate());
        user.setLastModifiedDate(this.getLastModifiedDate());
        user.setKinderGardensIds(kinderGardens);
        user.setGroupsIds(groupsList);

        return user;
    }
}
