package br.com.backend.taskapp.mapper;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.mapper.mocks.MockTask;
import br.com.backend.taskapp.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.backend.taskapp.mapper.Mapper.parseObjList;
import static br.com.backend.taskapp.mapper.Mapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {
    MockTask mockObj;

    @BeforeEach
    public void setup() {
        mockObj = new MockTask();
    }

    @Test
    public void parseEntityToDtoObjTest() {
        TaskDTO taskDtoAfterParse = parseObject(mockObj.mockTask(), TaskDTO.class);

        assertEquals(0, taskDtoAfterParse.getId());
        assertEquals("name: 0", taskDtoAfterParse.getName());
        assertEquals("historic: 0", taskDtoAfterParse.getHistoric());
        assertEquals("stage: 0", taskDtoAfterParse.getStage());
        assertEquals("end date: 0", taskDtoAfterParse.getEndDate());
        assertEquals("start date: 0", taskDtoAfterParse.getStartDate());
    }

    @Test
    public void parseDtoObjToEntityTest() {
        Task taskAfterParse = parseObject(mockObj.mockTaskDTO(), Task.class);

        assertEquals(0, taskAfterParse.getId());
        assertEquals("name: 0", taskAfterParse.getTitle());
        assertEquals("historic: 0", taskAfterParse.getHistoric());
        assertEquals("stage: 0", taskAfterParse.getStatus());
        assertEquals("end date: 0", taskAfterParse.getCloseDate());
        assertEquals("start date: 0", taskAfterParse.getCreateDate());
    }

    @Test
    public void parseEntityListToDtoObjList() {
        List<TaskDTO> listDTOAfterParse = parseObjList(mockObj.mockTaskList(2), TaskDTO.class);

        assertEquals(2, listDTOAfterParse.size());

        assertEquals(mockObj.mockTaskList(2).get(1).getId(), listDTOAfterParse.get(1).getId());
        assertEquals(mockObj.mockTaskList(2).get(1).getTitle(), listDTOAfterParse.get(1).getName());
        assertEquals(mockObj.mockTaskList(2).get(1).getHistoric(), listDTOAfterParse.get(1).getHistoric());
        assertEquals(mockObj.mockTaskList(2).get(1).getStatus(), listDTOAfterParse.get(1).getStage());
        assertEquals(mockObj.mockTaskList(2).get(1).getCloseDate(), listDTOAfterParse.get(1).getEndDate());
        assertEquals(mockObj.mockTaskList(2).get(1).getCreateDate(), listDTOAfterParse.get(1).getStartDate());
    }

    @Test
    public void parseDtoObjListToEntityList() {
        List<Task> listEntityAfterParse = parseObjList(mockObj.mockTaskDTOList(2), Task.class);

        assertEquals(2, listEntityAfterParse.size());

        assertEquals(mockObj.mockTaskDTOList(2).get(1).getId(), listEntityAfterParse.get(1).getId());
        assertEquals(mockObj.mockTaskDTOList(2).get(1).getName(), listEntityAfterParse.get(1).getTitle());
        assertEquals(mockObj.mockTaskDTOList(2).get(1).getHistoric(), listEntityAfterParse.get(1).getHistoric());
        assertEquals(mockObj.mockTaskDTOList(2).get(1).getStage(), listEntityAfterParse.get(1).getStatus());
        assertEquals(mockObj.mockTaskDTOList(2).get(1).getStartDate(), listEntityAfterParse.get(1).getCreateDate());
        assertEquals(mockObj.mockTaskDTOList(2).get(1).getEndDate(), listEntityAfterParse.get(1).getCloseDate());
    }
}
