package com.todo.app.repository;
import com.todo.app.domain.ToDo;
import com.todo.app.domain.ToDoStatus;
import org.springframework.data.repository.CrudRepository;


public interface ToDoRepository extends CrudRepository<ToDo, Long> {
        Iterable<ToDo> findAllByStatus(ToDoStatus status);
}
