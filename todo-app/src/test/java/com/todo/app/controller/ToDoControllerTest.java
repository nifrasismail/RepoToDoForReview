package com.todo.app.controller;

import com.todo.app.AppConfig;
import com.todo.app.domain.ToDo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ToDoControllerTest {

    @Autowired
    private ToDoController controller;

    @Test
    public void addTodo() throws Exception {
        ToDo todo = new ToDo();
        todo.setDescription("Hello");
        ResponseEntity<ToDo> responseEntity = controller.save(todo);
        ToDo toDo1 = responseEntity.getBody();

        ResponseEntity<ToDo> responseEntity1 = controller.findOne(toDo1.getId());
        ToDo toDo2 = responseEntity1.getBody();

        Assert.assertEquals(toDo1.getId(), toDo2.getId());
    }




}