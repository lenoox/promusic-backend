package com.lenoox.promusic.users.mapper;

import com.lenoox.promusic.common.exception.PasswordIncorrectException;
import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.RoleRepository;
import com.lenoox.promusic.users.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    private final BCryptPasswordEncoder passwordEncoder;
    private final  RoleRepository roleRepository;
    private final UserRepository userRepository;
    public UserMapper(
            BCryptPasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User paramToEntity(UserParam userParam, String emailDomainAuth,Boolean isUpdate) {
        User user = new User();
        if(isUpdate){
            User userSaved = userRepository.getUserByUsername(emailDomainAuth).get();
            boolean passwordmatches = passwordEncoder.matches(userParam.getPassword(), userSaved.getPassword());
            if(passwordmatches){
                user.setId(userSaved.getId());
                user.setUsername(userSaved.getUsername());
                user.setRole(userSaved.getRole());
                user.setCreatedDate(userSaved.getCreatedDate());
                user.setPassword(userSaved.getPassword());
            } else {
                throw new PasswordIncorrectException();
            }
        } else{
            user.setUsername(userParam.getUsername());
            if(userParam.getUsername().endsWith(emailDomainAuth)){
                user.setRole(roleRepository.getByRole(RoleType.EMPLOYEE.toString()));
            } else {
                user.setRole(roleRepository.getByRole(RoleType.USER.toString()));
            }
            user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        }
        user.setFirstName(userParam.getFirstName());
        user.setLastName(userParam.getLastName());
        user.setAddress(userParam.getAddress());
        user.setPhoneNumber(userParam.getPhoneNumber());
        user.setCity(userParam.getCity());
        user.setActive(true);
        return user;
    }

    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setCity(user.getCity());
        userDto.setActive(user.getActive());
        return userDto;
    }
}
