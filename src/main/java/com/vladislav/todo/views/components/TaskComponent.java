package com.vladislav.todo.views.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vladislav.todo.entities.Task;

public class TaskComponent extends Div {

    private final Task task;

    public TaskComponent(Task task) {
        this.task = task;

        addClassName("task-component");

        this.add(
                new Checkbox(task.getCompleted()),
                new Text(task.getContent())
        );
    }
}
