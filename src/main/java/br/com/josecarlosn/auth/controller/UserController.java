package br.com.josecarlosn.auth.controller;

import br.com.josecarlosn.auth.entity.User;
import br.com.josecarlosn.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok(userService.list());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.ok("User deleted.");
    }
}
