package com.lenoox.promusic.users;

import com.lenoox.promusic.common.dtos.ApiResponse;
import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.service.AuthenticationFacadeService;
import com.lenoox.promusic.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse getAll(){
        log.info(String.format("received request to list user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        return new ApiResponse(HttpStatus.OK, userService.getAll());
    }

    @Secured({RoleType.Code.ROLE_EMPLOYEE, RoleType.Code.ROLE_USER})
    @GetMapping(value = "/me")
    public ApiResponse getUser(){
        log.info(String.format("received request to update user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        String email = authenticationFacadeService.getAuthentication().getPrincipal().toString();
        return new ApiResponse(HttpStatus.OK, userService.getByEamil(email));
    }
    @PostMapping
    public ApiResponse create(@RequestBody UserParam user){
        log.info("received request to create user");
        return new ApiResponse(HttpStatus.OK, userService.save(user));
    }

    @Secured({RoleType.Code.ROLE_EMPLOYEE})
    @DeleteMapping(value = "/{id}")
    public ApiResponse delete(@PathVariable(value = "id") Long id){
        log.info(String.format("received request to delete user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        userService.delete(id);
        return new ApiResponse(HttpStatus.OK, "deleted successfully");
    }

}