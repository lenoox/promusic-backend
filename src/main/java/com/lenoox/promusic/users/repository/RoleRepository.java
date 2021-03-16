package com.lenoox.promusic.users.repository;

import com.lenoox.promusic.users.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "SELECT * FROM roles where role_name IN (:roles)", nativeQuery = true)
    Role find(@Param("roles") List<String> roles);
    @Query(value = "SELECT * FROM roles where role_name = :role", nativeQuery = true)
    Role findRole(@Param("role") String role);

}

