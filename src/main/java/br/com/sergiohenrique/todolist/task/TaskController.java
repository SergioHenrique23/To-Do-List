package br.com.sergiohenrique.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskRepository repository;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel){

        var taskCreated = this.repository.save(taskModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }
}
