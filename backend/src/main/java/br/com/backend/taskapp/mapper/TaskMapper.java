package br.com.backend.taskapp.mapper;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getTitle());
        taskDTO.setStage(task.getStatus());
        taskDTO.setStartDate(task.getCreateDate());
        taskDTO.setEndDate(task.getCloseDate());
        taskDTO.setHistoric(task.getHistoric());

        return taskDTO;
    }

    public Task toTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getName());
        task.setStatus(taskDTO.getStage());
        task.setCreateDate(taskDTO.getStartDate());
        task.setCloseDate(taskDTO.getEndDate());
        task.setHistoric(taskDTO.getHistoric());

        return task;
    }

    public List<TaskDTO> toTaskDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toTaskDTO)
                .collect(Collectors.toList());
    }

    public List<Task> toTaskList(List<TaskDTO> taskDTOs) {
        return taskDTOs.stream()
                .map(this::toTask)
                .collect(Collectors.toList());
    }
}
