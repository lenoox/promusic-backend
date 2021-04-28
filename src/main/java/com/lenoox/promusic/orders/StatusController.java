package com.lenoox.promusic.orders;

import com.lenoox.promusic.orders.dtos.StatusDto;
import com.lenoox.promusic.orders.param.StatusParam;
import com.lenoox.promusic.orders.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    private static final Logger log = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<StatusDto>> getAll(){
        return ResponseEntity.ok().body(statusService.getAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<StatusDto>  getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(statusService.getById(id));
    }
    @PostMapping
    public ResponseEntity<StatusDto> create(@RequestBody StatusParam status){
        return ResponseEntity.ok().body(statusService.create(status));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<StatusDto> update(@PathVariable(value = "id") Long id,
                                           @RequestBody StatusParam status){
        return ResponseEntity.ok().body(statusService.update(id,status));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        statusService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
