package com.lenoox.promusic.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String phoneNumber;
    private String city;
    private Boolean active;
}
