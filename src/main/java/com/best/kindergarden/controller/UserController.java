package com.best.kindergarden.controller;
import com.best.kindergarden.model.dto.Response;
import com.best.kindergarden.model.dto.UserDTO;
import com.best.kindergarden.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UserDTO userDTO) {
        log.debug("Request came to save user   :{}", userDTO);
        UserDTO save = userService.save(userDTO);
        return ResponseEntity.ok(new Response<UserDTO>("User was successfully saved", save, HttpStatus.OK.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody @Valid UserDTO userDTO) {
        log.debug("Request came to edit user  request :{}", userDTO);
        UserDTO edit = userService.edit(id, userDTO);
        return ResponseEntity.ok(new Response<UserDTO>("User was updated", edit, HttpStatus.OK.value()));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(name = "firstname", required = false) String firstname,
                                     @RequestParam(name = "lastname", required = false) String lastname,
                                     @RequestParam(name = "page") Integer page,
                                     @RequestParam(name = "size") Integer size) {
        log.debug("Request came to user  find all");
        PageImpl<UserDTO> users = userService.findAll(firstname, lastname, page,size);
        return ResponseEntity.ok(new Response<PageImpl<UserDTO>>(" find all group ",users, HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        log.debug("Request came to group findById  request :{}", id);
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(new Response<UserDTO>(user, HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok(new Response<>("user  was successfully deleted", HttpStatus.OK.value()));
    }


}
