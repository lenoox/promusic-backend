package com.lenoox.promusic.users.Param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordParam {
    private String oldPassword;
    private String newPassword;
}
