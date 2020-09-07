package com.vladislav.todo.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vladislav.todo.services.TaskService;
import com.vladislav.todo.services.UserService;
import com.vladislav.todo.views.components.TaskComponent;

@PageTitle("All tasks | TODO")
@Route(value = "")
public class TasksView extends VerticalLayout {

    private final TaskService taskService;
    private final UserService userService;

    public TasksView(
            TaskService taskService,
            UserService userService
    ) {
        this.taskService = taskService;
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        this.add(new H1("Tasks"));

        userService.getAuthenticatedUser().getTasks()
                .stream()
                .map(TaskComponent::new)
                .forEach(this::add);
    }
}
