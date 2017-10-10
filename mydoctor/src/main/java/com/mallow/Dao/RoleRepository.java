package com.mallow.Dao;

import com.mallow.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brahim on 8/15/17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
