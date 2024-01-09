package br.com.backend.taskapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="tasks")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderColumn()
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "status", length = 80)
    private String status;

    private String createDate;

    private String closeDate;

    @Column(name = "historic", length = 120)
    private String historic;

//TODO Revisar code

//    public enum TaskStatus {
//        TO_DO,
//        IN_PROGRESS,
//        DONE
//    }
}
