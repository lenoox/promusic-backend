package com.lenoox.promusic.users;

import com.lenoox.promusic.common.dtos.ApiResponse;
import com.lenoox.promusic.users.dtos.UserDto;
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

    public static final String SUCCESS = "success";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    @Secured({ROLE_ADMIN})
    @GetMapping
    public ApiResponse listUser(){
        log.info(String.format("received request to list user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        return new ApiResponse(HttpStatus.OK, SUCCESS, userService.findAll());
    }

    @PostMapping
    public ApiResponse create(@RequestBody UserDto user){
        log.info("received request to create user");
        return new ApiResponse(HttpStatus.OK, SUCCESS, userService.save(user));
    }

    @Secured({ROLE_ADMIN, ROLE_EMPLOYEE})
    @GetMapping(value = "/me")
    public ApiResponse getUser(){
        log.info(String.format("received request to update user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        String email = authenticationFacadeService.getAuthentication().getPrincipal().toString();
        return new ApiResponse(HttpStatus.OK, SUCCESS, userService.findEmail(email));
    }

    @Secured({ROLE_ADMIN})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        log.info(String.format("received request to delete user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        userService.delete(id);
    }

}