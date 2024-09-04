package com.example.model;

import java.time.LocalDate;

public class Todo {
    private int id;
    private String task;
    private boolean completed;
    private LocalDate dueDate;

    public Todo(int id, String task, LocalDate dueDate) {
        this.id = id;
        this.task = task;
        this.completed = false;
        this.dueDate = dueDate;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}