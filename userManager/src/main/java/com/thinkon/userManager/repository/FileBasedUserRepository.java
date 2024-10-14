package com.thinkon.userManager.repository;

import com.thinkon.userManager.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class FileBasedUserRepository{
    private static final String FILE_PATH = "users.json";
    private final AtomicLong idGenerator = new AtomicLong(0);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<User> loadUsers(){
        try{
            File file = new File(FILE_PATH);
            if(file.exists()){
                return objectMapper.readValue(file, new TypeReference<List<User>>() {});
            } else {
                return new ArrayList<>();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users){
        try{
            objectMapper.writeValue(new File(FILE_PATH), users);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public User saveUser(User user){
        List<User> users = loadUsers();
        if(user.getID()== null){
            user.setID(idGenerator.incrementAndGet());
        }
        users.removeIf(u-> u.getID().equals(user.getID()));
        users.add(user);
        saveUsers(users);
        return user;
    }

    public Optional<User> findUserById(Long id){
        return loadUsers().stream().filter(user->user.getID().equals(id)).findFirst();
    }
    public boolean deleteUser(Long id){
        List<User> users = loadUsers();
        boolean removed = users.removeIf(user-> user.getID().equals(id));
        if(removed){
            saveUsers(users);
        }
        return removed;
    }
}