package com.vladislav.todo.views.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vladislav.todo.entities.Task;
import lombok.Getter;

public class TaskForm extends FormLayout {

    private final TextField content = new TextField("Content") {{
        setSizeFull();
    }};

    private final Button save = new Button("Save") {{
        addClickListener(click -> validateAndSave());
        addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }};

    private final Binder<Task> taskBinder = new Binder<>(Task.class) {{
        addStatusChangeListener(event -> save.setEnabled(taskBinder.isValid()));
    }};

    public TaskForm() {
        addClassName("task-form");

        taskBinder.bindInstanceFields(this);

        add(content, save);
    }

    private void validateAndSave() {
        if (taskBinder.isValid()) {
            fireEvent(new SaveEvent(this, taskBinder.getBean()));
        }
    }

    public void setTask(Task task) {
        taskBinder.setBean(task);
    }

    public static abstract class TaskFormEvent extends ComponentEvent<TaskForm> {

        @Getter
        private final Task task;

        public TaskFormEvent(TaskForm source, Task task) {
            super(source, false);
            this.task = task;
        }
    }

    public static class SaveEvent extends TaskFormEvent {
        public SaveEvent(TaskForm source, Task task) {
            super(source, task);
        }
    }
}
