package com.vladislav.todo.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Entity(name = "Task")
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder.Default
    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

}
