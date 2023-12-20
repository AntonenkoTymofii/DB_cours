package com.example.db_project.repository;

import com.example.db_project.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByFirstname(String firstname);
    UserEntity findByNickname(String nickname);
    UserEntity findByEmail(String email);
}
