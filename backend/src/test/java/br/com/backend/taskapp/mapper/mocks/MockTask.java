package br.com.backend.taskapp.mapper.mocks;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MockTask {

    public Task mockTask() {

        return mockTask(0);
    }

    public TaskDTO mockTaskDTO() {

        return mockTaskDTO(0);
    }

    public Task mockTask(Integer number) {

        Task task = new Task();
        task.setId(number.longValue());
        task.setTitle("name: " + number);
        task.setHistoric("historic: " + number);
        task.setStatus("stage: " + number);
        task.setCreateDate("start date: " + number);
        task.setCloseDate("end date: " + number);

        return task;
    }

    public TaskDTO mockTaskDTO(Integer number) {

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(number.longValue());
        taskDTO.setName("name: " + number.toString());
        taskDTO.setHistoric("historic: " + number);
        taskDTO.setStage("stage: " + number);
        taskDTO.setStartDate("start date: " + number);
        taskDTO.setEndDate("end date: " + number);

        return taskDTO;
    }

    public List<Task> mockTaskList(Integer listSize) {

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            taskList.add(mockTask(i));
        }
        return taskList;
    }

    public List<TaskDTO> mockTaskDTOList(Integer listSize) {

        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            taskDTOList.add(mockTaskDTO(i));
        }
        return taskDTOList;
    }
}
