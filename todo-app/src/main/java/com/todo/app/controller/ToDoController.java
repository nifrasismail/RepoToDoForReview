package com.todo.app.controller;

import com.todo.app.domain.ToDo;
import com.todo.app.domain.ToDoStatus;
import com.todo.app.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api")
public class ToDoController {

    private final Logger log = LoggerFactory.getLogger(ToDoController.class);

    @Autowired
    private ToDoRepository repository;

    /**
     * Save todos to database
     *
     * @param toDo
     * @return
     */
    @PostMapping("/todo")
    public ResponseEntity<ToDo> save(@RequestBody ToDo toDo) {
        log.debug("Creating Object  {}", toDo);
        toDo.setCreatedDate(new Date());
        log.debug("Created Date {}", toDo.getCreatedDate());
        ToDo savedTodo = repository.save(toDo);
        return ResponseEntity.ok(savedTodo);
    }

    /**
     * Find all todos
     *
     * @return
     */
    @GetMapping("/todo")
    public ResponseEntity<?> findAll() {
        Iterable<ToDo> toDos = repository.findAll();
        log.debug("Listing Object  {} ", toDos);
        return ResponseEntity.ok(toDos);
    }

    /**
     * Get todo by Id
     *
     * @param id
     * @return
     */
    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> findOne(@PathVariable Long id) {
        log.debug("Received Id {}", id);
        ToDo toDo = repository.findOne(id);
        return ResponseEntity.ok(toDo);
    }

    /**
     * Delete particular todo
     *
     * @param id
     */
    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("Deleted Id {}", id);
        repository.delete(id);
    }

    /**
     * Getting the pending todos
     *
     * @return
     */
    @GetMapping("/todo/pending")
    public ResponseEntity<?> getPendingToDos() {
        Iterable<ToDo> toDos = repository.findAllByStatus(ToDoStatus.PENDING);
        return ResponseEntity.ok(toDos);
    }

    /**
     * Getting the completed todos
     *
     * @return
     */
    @GetMapping("/todo/completed")
    public ResponseEntity<?> getCompletedToDos() {
        Iterable<ToDo> toDos = repository.findAllByStatus(ToDoStatus.COMPLETED);
        return ResponseEntity.ok(toDos);
    }


}
