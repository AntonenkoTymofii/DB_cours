package com.example.db_project.controller;

import com.example.db_project.entity.UserEntity;
import com.example.db_project.exception.EmailAlreadyExistException;
import com.example.db_project.exception.NicknameAlreadyExistException;
import com.example.db_project.exception.UserNotFoundException;
import com.example.db_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registrationUser(user);
            return ResponseEntity.ok("Користувач успішно зареєстрований");
        } catch (EmailAlreadyExistException | NicknameAlreadyExistException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception exception){
            return ResponseEntity.badRequest().body("Відбулась помилка");
        }
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id){
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception exception){
            return ResponseEntity.badRequest().body("Відбулась помилка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok("Було успішно видалено користувача з id: " +
                    userService.deleteUser(id));
        } catch (UserNotFoundException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception exception){
            return ResponseEntity.badRequest().body("Відбулась помилка");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestParam Long id,
                                       @RequestBody UserEntity user){
        try {
            userService.updateUser(id, user);
            return ResponseEntity.ok("Дані користувача були оновлені успішно");
        }catch (UserNotFoundException | EmailAlreadyExistException |
                NicknameAlreadyExistException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception exception){
            return ResponseEntity.badRequest().body("Відбулась помилка");
        }
    }
}
