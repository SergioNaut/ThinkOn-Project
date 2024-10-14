package com.thinkon.userManager.service;

import com.thinkon.userManager.model.User;
import com.thinkon.userManager.repository.UserRepository;
import com.thinkon.userManager.repository.FileBasedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService{

    @Autowired
    private FileBasedUserRepository userRepo;

    //Creating a new user and saving in the repository
    // POST /users - create a new user

    public User createUser(User user){
        return userRepo.saveUser(user);
    }

    //Getting an user using ID
    //GET /users/{id}

    public User getUser(Long myId){
        return userRepo.findUserById(myId)
            .orElseThrow(() -> new EntityNotFoundException("CANNOT FIND USER WITH ID: " + myId));
    }

    //Getting list of users
    //GET /users - get a list of users

    public List<User> getAllUsers()
    {
        return userRepo.loadUsers();
    }

    //Updating an existing user and saving in the repository
    //PUT /users/{id}

    public User updateUser(Long myId, User updatedUser){
        User user = getUser(myId);

        user.setUserName(updatedUser.getUserName());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());

        return userRepo.saveUser(user);
    }

    //Deleting a user
    //DELETE /users/{id}
    public void deleteUser(Long myId){
        if (!userRepo.deleteUser(myId)) {
            throw new EntityNotFoundException("NO USER WITH ID: " + myId);
        }
    }

}