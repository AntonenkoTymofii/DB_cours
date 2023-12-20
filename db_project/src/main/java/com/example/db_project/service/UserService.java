package com.example.db_project.service;

import com.example.db_project.entity.UserEntity;
import com.example.db_project.exception.EmailAlreadyExistException;
import com.example.db_project.exception.NicknameAlreadyExistException;
import com.example.db_project.exception.UserNotFoundException;
import com.example.db_project.model.User;
import com.example.db_project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registrationUser(UserEntity user) throws EmailAlreadyExistException, NicknameAlreadyExistException {
        if (userRepo.findByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistException("Користувач з такою електронною поштою вже існує");
        } else if (userRepo.findByNickname(user.getNickname()) != null) {
            throw new NicknameAlreadyExistException("Користувач з таким нікнеймом вже існує");
        }
        return userRepo.save(user);
    }

    public UserEntity getUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null){
            throw new UserNotFoundException("Такого користувача не найдено");
        }
        return user;
    }

    public Long deleteUser(Long id) throws UserNotFoundException {
        if (userRepo.findById(id) == null){
            throw new UserNotFoundException("Такого користувача вже не існує");
        }
        userRepo.deleteById(id);
        return id;
    }
    public UserEntity updateSurvey(Long id, UserEntity user)
            throws EmailAlreadyExistException, NicknameAlreadyExistException,
            UserNotFoundException {
        UserEntity userEntity = userRepo.findById(id).get();
        if(userEntity == null){
            throw new UserNotFoundException("Такого користувача не існує");
        }
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickname());
        if(userRepo.findByNickname(userEntity.getNickname()) != null){
            throw new NicknameAlreadyExistException("Користувач з таким нікнеймом вже існує");
        } else if (userRepo.findByEmail(userEntity.getEmail()) != null){
            throw new EmailAlreadyExistException("Користувач з такою електронною поштою вже існує");
        }
        return userRepo.save(userEntity);
    }
}
