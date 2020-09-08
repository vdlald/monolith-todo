package com.vladislav.todo.views.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vladislav.todo.entities.Task;

public class TaskComponent extends Div {

    private final Task task;

    private final Text contentText;
    private final Checkbox completedCheckbox;

    public TaskComponent(Task task) {
        this.task = task;

        addClassName("task-component");

        contentText = new Text(task.getContent());
        completedCheckbox = new Checkbox(task.getCompleted());

        this.add(
                completedCheckbox,
                contentText
        );
    }
}
