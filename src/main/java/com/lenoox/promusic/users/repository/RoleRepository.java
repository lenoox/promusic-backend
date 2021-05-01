package com.lenoox.promusic.users.repository;

import com.lenoox.promusic.users.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "SELECT r.* FROM roles r where r.role_name IN (:roles)", nativeQuery = true)
    Role getByRoles(@Param("roles") List<String> roles);
    @Query(value = "SELECT r.* FROM roles r where r.role_name = :role", nativeQuery = true)
    Role getByRole(@Param("role") String role);

}

