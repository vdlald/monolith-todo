package com.vladislav.todo.views.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;
import com.vladislav.todo.entities.Task;
import lombok.Getter;

public class TaskComponent extends Div {

    private final Text contentText;
    private final Checkbox completedCheckbox;

    private final Binder<Task> taskBinder = new Binder<>(Task.class);

    public TaskComponent(Task task) {
        addClassName("task-component");

        contentText = new Text(task.getContent());
        completedCheckbox = new Checkbox(task.getCompleted());
        completedCheckbox.addClickListener(click -> onPropertyChange());

        taskBinder.setBean(task);
        taskBinder.bind(completedCheckbox, "completed");

        this.add(
                completedCheckbox,
                contentText
        );
    }

    private void onPropertyChange() {
        fireEvent(new UpdateEvent(this, taskBinder.getBean()));
    }

    public static abstract class TaskComponentEvent extends ComponentEvent<TaskComponent> {

        @Getter
        private final Task task;

        public TaskComponentEvent(TaskComponent source, Task task) {
            super(source, false);
            this.task = task;
        }
    }

    public static class UpdateEvent extends TaskComponentEvent {
        public UpdateEvent(TaskComponent source, Task task) {
            super(source, task);
        }
    }
}

