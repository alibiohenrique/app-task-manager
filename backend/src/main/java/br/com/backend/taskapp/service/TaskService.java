package br.com.backend.taskapp.service;

import br.com.backend.taskapp.controller.TaskController;
import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.model.Task;
import br.com.backend.taskapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.backend.taskapp.mapper.Mapper.parseObjList;
import static br.com.backend.taskapp.mapper.Mapper.parseObject;
import static java.util.stream.Collectors.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public ResponseEntity<?> findById(Long id) {
        log.info("[GET BY ID] Find task by id...");

        Optional<Task> entity = repository.findById(id);
        if (entity.isEmpty()) return ResponseEntity.notFound().build();

        var dto = parseObject(entity.get(), TaskDTO.class);
        dto.add(linkTo(methodOn(TaskController.class).findById(id)).withSelfRel());

        return ResponseEntity.of(Optional.of(dto));
    }

    public List<ResponseEntity<?>> getAllTask() {
        log.info("[GET ALL] Finding all tasks...");
        List<TaskDTO> taskDTOList = parseObjList(repository.findAll(), TaskDTO.class);
        return taskDTOList.stream()
                .map(t -> {
                    t.add(linkTo(methodOn(TaskController.class).findById(t.getId())).withSelfRel());
                    return ResponseEntity.ok(t);
                })
                .collect(toList());
    }

    public ResponseEntity<?> create(TaskDTO taskDTO) {
        log.info("[REQUEST] " + taskDTO.toString());
        var entity = parseObject(taskDTO, Task.class);
        var savedEntity = repository.save(entity);
        var dto = parseObject(savedEntity, TaskDTO.class);
        log.info("[RESPONSE] " + dto.toString());
        dto.add(linkTo(methodOn(TaskController.class).findById(dto.getId())).withSelfRel());

        return ResponseEntity.ok(dto);
    }
}
