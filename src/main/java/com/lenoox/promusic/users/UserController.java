package com.lenoox.promusic.users;

import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.common.services.UserDetailsImpl;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;
import com.lenoox.promusic.users.service.AuthenticationFacadeService;
import com.lenoox.promusic.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    @Secured({RoleType.Code.ROLE_EMPLOYEE})
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        log.info(String.format("received request to list user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        return ResponseEntity.ok().body(userService.getAll());
    }

    @Secured({RoleType.Code.ROLE_EMPLOYEE, RoleType.Code.ROLE_USER})
    @GetMapping(value = "/me")
    public ResponseEntity<UserWithRolesDTO> getUser(){
        log.info(String.format("received request to update user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        Object principal = authenticationFacadeService.getAuthentication().getPrincipal();
        String email = ((UserDetailsImpl) principal).getUser().getEmail();
        return ResponseEntity.ok().body(userService.getByEamil(email));
    }
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserParam user){
        log.info("received request to create user");
        return ResponseEntity.ok().body(userService.save(user));
    }

    @Secured({RoleType.Code.ROLE_EMPLOYEE})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        log.info(String.format("received request to delete user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        userService.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }

}