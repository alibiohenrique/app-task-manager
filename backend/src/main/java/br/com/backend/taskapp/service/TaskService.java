package br.com.backend.taskapp.service;

import br.com.backend.taskapp.controller.TaskController;
import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.exceptions.ResourceNotFoundException;
import br.com.backend.taskapp.mapper.TaskMapper;
import br.com.backend.taskapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;

    public TaskDTO findById(Long id) {
        log.info("[GET BY ID] Find task by id...");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("[TASK-APP] No task for this id"));
        var dto = taskMapper.toTaskDTO(entity);
        dto.add(linkTo(methodOn(TaskController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<TaskDTO> getAllTask() {
        log.info("[GET ALL] Finding all tasks...");
        var tasks = taskMapper.toTaskDTOList(repository.findAll());
        tasks.forEach(t -> t.add(linkTo(methodOn(TaskController.class).findById(t.getId())).withSelfRel()));
        return tasks;
    }

    public TaskDTO create(TaskDTO taskDTO) {
        log.info("[POST] Creating a task!");
        log.info("[REQUEST] "); //TODO PRINTAR REQUEST
        var entity = taskMapper.toTask(taskDTO);
        log.info("[DTO TO ENTITY] "); //TODO PRINTAR ENTITY
        var savedEntity = repository.save(entity);
        var dto = taskMapper.toTaskDTO(savedEntity);
        log.info("[RESPONSE] "); //TODO PRINTAR RESPONSE
        dto.add(linkTo(methodOn(TaskController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }
}
