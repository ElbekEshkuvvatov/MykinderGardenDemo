package com.best.kindergarden.controller;
import com.best.kindergarden.model.dto.GroupDTO;
import com.best.kindergarden.model.dto.Response;
import com.best.kindergarden.service.impl.GroupServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/*
 1. Validation error message to'g'irlash kerak Response.class qaytaradigan qib
 2. Log yozadigan qilish kerak
 3. findBy nimadur qidirvotkanda topolmasa Optionalni o'zidan elseThrow() metodida exeption tashash kerak

 */

/*
11.04.2022
1. Log larni serviska ob o'tish kerak
2. findAll metodiga filter qo'shish kerak
4. Pageable qilish
 */

@RestController
@Log4j2
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupService;

    //Create
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid GroupDTO groupDTO) {
        log.debug("Request came to save group   :{}", groupDTO);
        GroupDTO save = groupService.save(groupDTO);
        return ResponseEntity.ok(new Response<GroupDTO>("user was saved", save, HttpStatus.OK.value()));
    }


    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody @Valid GroupDTO groupDTO) {
        log.debug("Request came to edit group  request :{}", groupDTO);
        GroupDTO edit = groupService.edit(id, groupDTO);
        return ResponseEntity.ok(new Response<GroupDTO>("User was updated", edit, HttpStatus.OK.value()));
    }

    // GetAll
    @GetMapping
    public ResponseEntity<?> findAll(                       @RequestParam(name = "name" ,required = false) String name,
                                                            @RequestParam(name = "maxChild" ,required = false) Integer maxChild,
                                                            @RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size) {
        log.debug("Request came to group  find all");
        PageImpl<GroupDTO> response  = groupService.findAll(name, maxChild, page, size);
        Response<PageImpl<GroupDTO>> apiResponse = new Response<>("find all group", response, HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    //GetById
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Integer id) {
        log.debug("Request came to findById group  request :{}", id);
        GroupDTO group = groupService.findById(id);
        return ResponseEntity.ok(new Response<GroupDTO>(group, HttpStatus.OK.value()));
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.debug("Request came to delete group  request :{}", id);
        groupService.delete(id);
        return ResponseEntity.ok(new Response<>("group  was successfully deleted", HttpStatus.OK.value()));
    }
}
