package com.example.util;

import com.example.model.Todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TodoStorage {
    private static final List<Todo> todos = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public static void addTodo(String task, LocalDate dueDate) {
        todos.add(new Todo(idGenerator.getAndIncrement(), task, dueDate));
    }

    public static List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }

    public static void deleteTodo(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public static void updateTodo(int id, String task, LocalDate dueDate, boolean completed) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setTask(task);
                todo.setDueDate(dueDate);
                todo.setCompleted(completed);
                break;
            }
        }
    }

    public static Todo getTodoById(int id) {
        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Todo> getFilteredTodos(String filter) {
        switch (filter) {
            case "active":
                return todos.stream().filter(todo -> !todo.isCompleted()).collect(Collectors.toList());
            case "completed":
                return todos.stream().filter(Todo::isCompleted).collect(Collectors.toList());
            default:
                return getAllTodos();
        }
    }
}
