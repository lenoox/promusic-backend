package com.lenoox.promusic.users.Param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserParam {
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private String city;
}
