package com.mallow.Dao;

import com.mallow.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brahim on 8/20/17.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String>{



}
