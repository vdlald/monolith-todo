package com.vladislav.todo.views;

import com.vaadin.flow.component.ComponentEvent;
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
    private final Div tasksContainer = new Div();

    public TasksView(
            TaskService taskService,
            UserService userService
    ) {
        this.taskService = taskService;
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        this.add(new H2("Add task"), new Div(taskForm), new H2("Tasks"), tasksContainer);

        final User user = userService.getAuthenticatedUser();
        allUserTasks = taskService.getAllUserTasks(user.getId());

        allUserTasks.stream()
                .sorted((t1, t2) -> {
                    if (t1.getCompleted().equals(t2.getCompleted())) {
                        return 0;
                    } else if (t1.getCompleted()) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .map(this::newTaskComponent)
                .forEach(tasksContainer::add);
    }

    private void onAddTask(TaskForm.SaveEvent event) {
        final Task task = event.getTask();
        task.setUser(userService.getAuthenticatedUser());
        final Task saved = taskService.saveTask(task);

        allUserTasks.add(saved);
        tasksContainer.add(newTaskComponent(saved));

        taskForm.setTask(new Task());
    }

    private TaskComponent newTaskComponent(Task task) {
        return new TaskComponent(task) {{
            addListener(UpdateEvent.class, TasksView.this::onUpdateTask);
        }};
    }

    private void onUpdateTask(TaskComponent.UpdateEvent event) {
        final Task task = event.getTask();
        taskService.saveTask(task);
    }
}
