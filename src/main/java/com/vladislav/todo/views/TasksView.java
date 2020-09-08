package com.vladislav.todo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vladislav.todo.entities.Task;
import com.vladislav.todo.entities.User;
import com.vladislav.todo.services.TaskService;
import com.vladislav.todo.services.UserService;
import com.vladislav.todo.views.components.TaskComponent;
import com.vladislav.todo.views.components.TaskForm;

import java.util.List;

@PageTitle("All tasks | TODO")
@Route(value = "")
public class TasksView extends VerticalLayout {

    private final TaskService taskService;
    private final UserService userService;

    private final TaskForm taskForm = new TaskForm() {{
        setTask(new Task());
        addListener(SaveEvent.class, TasksView.this::onAddTask);
    }};

    private final List<Task> allUserTasks;

    public TasksView(
            TaskService taskService,
            UserService userService
    ) {
        this.taskService = taskService;
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        this.add(new H2("Add task"), new Div(taskForm), new H2("Tasks"));

        final User user = userService.getAuthenticatedUser();
        allUserTasks = taskService.getAllUserTasks(user.getId());
        allUserTasks.stream()
                .map(TaskComponent::new)
                .forEach(this::add);
    }

    private void onAddTask(TaskForm.SaveEvent event) {
        final Task task = event.getTask();
        task.setUser(userService.getAuthenticatedUser());
        final Task saved = taskService.saveTask(task);

        allUserTasks.add(saved);
        this.add(new TaskComponent(saved));

        taskForm.setTask(new Task());
    }
}
