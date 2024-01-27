package br.com.backend.taskapp.controller;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @CrossOrigin
    @GetMapping
    public List<ResponseEntity<?>> findAllTask() {
        return service.getAllTask();
    }

    @CrossOrigin
    @GetMapping(value = "{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TaskDTO task) {
        return service.create(task);
    }
}
