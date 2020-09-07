package com.vladislav.todo;

import com.vladislav.todo.entities.Task;
import com.vladislav.todo.entities.User;
import com.vladislav.todo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DemoApplicationRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<Task> tasks = List.of(
                new Task().setContent("Content 1"),
                new Task().setContent("Content 2"));
        final User user = new User()
                .setUsername("demo")
                .setPassword(passwordEncoder.encode("demo"))
                .setTasks(tasks);
        tasks.forEach(task -> task.setUser(user));
        final User saved = userRepository.save(user);
    }
}
