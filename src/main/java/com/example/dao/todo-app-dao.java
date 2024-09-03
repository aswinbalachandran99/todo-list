package com.example.dao;

import com.example.model.Todo;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    private static List<Todo> todos = new ArrayList<>();
    private static int idCounter = 1;

    static {
        todos.add(new Todo(idCounter++, "Learn Java", false));
        todos.add(new Todo(idCounter++, "Create a web app", false));
    }

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }

    public void addTodo(String task) {
        todos.add(new Todo(idCounter++, task, false));
    }

    public void toggleTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setCompleted(!todo.isCompleted());
                break;
            }
        }
    }

    public void deleteTodo(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }
}
