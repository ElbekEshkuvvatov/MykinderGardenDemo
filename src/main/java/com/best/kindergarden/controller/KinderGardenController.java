package com.best.kindergarden.controller;
import com.best.kindergarden.model.dto.KinderGardenDTO;
import com.best.kindergarden.model.dto.Response;
import com.best.kindergarden.service.KinderGardenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("api/kinder-garden")
public class KinderGardenController {

    final KinderGardenService kinderGardenService;

    public KinderGardenController(KinderGardenService kinderGardenService) {
        this.kinderGardenService = kinderGardenService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid KinderGardenDTO kinderGardenDTO){
        log.debug("Request came to save KinderGarden :{}", kinderGardenDTO);
        KinderGardenDTO save = kinderGardenService.save(kinderGardenDTO);
        return ResponseEntity.ok(new Response<KinderGardenDTO>("KinderGarden was successfully saved",save, HttpStatus.OK.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody @Valid KinderGardenDTO kinderGardenDTO){
        log.debug("Request came to edit KinderGarden :{}", kinderGardenDTO);
        KinderGardenDTO edit = kinderGardenService.edit(id, kinderGardenDTO);
        return ResponseEntity.ok(new Response<KinderGardenDTO>("KinderGarden was updated",edit,HttpStatus.OK.value()));
    }

    @GetMapping
    public ResponseEntity<?> findAll( @RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "page") Integer page,
                                      @RequestParam(name = "size") Integer size){
        log.debug("Request came to group  find all");
        PageImpl<KinderGardenDTO> findAll = kinderGardenService.findAll(name, page, size);
        return ResponseEntity.ok(new Response<PageImpl<KinderGardenDTO>>("All value was found in database ",findAll,HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
     ResponseEntity<?> findById(@PathVariable Integer id){
        log.debug("Request came to findById kinder-garden   :{}", id);
        KinderGardenDTO kinderGarden = kinderGardenService.findById(id);
        return ResponseEntity.ok(new Response<KinderGardenDTO>("such id kinderGarden was found", kinderGarden, HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        log.debug("Request came to delete kinder-garden :{}", id);
        kinderGardenService.delete(id);
        return ResponseEntity.ok(new Response<>("KinderGarden was deleted",HttpStatus.OK.value()));
    }
}
