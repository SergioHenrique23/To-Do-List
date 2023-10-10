package br.com.sergiohenrique.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        System.out.println(userModel.getName());
    }
    
}
