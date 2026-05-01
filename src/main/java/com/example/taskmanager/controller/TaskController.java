package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task create(@RequestBody Task task) {

        // role check
        if (!task.getAssignedTo().getRole().equals("ADMIN")) {
            throw new RuntimeException("Only ADMIN can create task");
        }

        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }
}