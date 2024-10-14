package com.thinkon.userManager.repository;

import com.thinkon.userManager.model.User;
import java.util.Optional;
//Used for basic CRUD operations
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Used to interact with the DB

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}