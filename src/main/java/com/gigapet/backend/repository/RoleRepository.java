package com.gigapet.backend.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gigapet.backend.models.Role;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public interface RoleRepository extends CrudRepository<Role, Long>
{
    @Transactional
    @Modifying
    @Query(value = "DELETE from UserRoles where userid = :userid")
    void deleteUserRolesByUserId(long userid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserRoles(userid, roleid) values (:userid, :roleid)", nativeQuery = true)
    void insertUserRoles(long userid, long roleid);

    Role findByNameIgnoreCase(String name);
}
