package br.com.backend.taskapp.testmapper;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.mapper.TaskMapper;
import br.com.backend.taskapp.model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskMapperTest {
  
  private final TaskMapper taskMapper = new TaskMapper();
  
  @Test
  public void testToTaskDto () {
    
    Task task = new Task();
    task.setId(1L);
    task.setTitle("Test");
    task.setStatus("In progress");
    task.setCloseDate("31/01/2000");
    task.setCreateDate("31/01/2023");
    task.setHistoric("Clean bathroom");
    
    TaskDTO taskDTO = taskMapper.toTaskDTO(task);
    assertEquals(task.getId(), taskDTO.getId());
    assertEquals(task.getTitle(), taskDTO.getName());
    assertEquals(task.getStatus(), taskDTO.getStage());
    assertEquals(task.getCloseDate(), taskDTO.getEndDate());
    assertEquals(task.getCreateDate(), taskDTO.getStartDate());
    assertEquals(task.getHistoric(), taskDTO.getHistoric());
  }
  
  @Test
  public void testToTaskDtoNotEquals () {
    
    TaskDTO taskDTOMock = new TaskDTO();
    taskDTOMock.setId(2L);
    taskDTOMock.setName("Lincola");
    taskDTOMock.setStage("Done");
    taskDTOMock.setStartDate("01/05/2023");
    taskDTOMock.setEndDate("05/05/2023");
    taskDTOMock.setHistoric("Finish mentorship");
    
    Task task = new Task();
    task.setId(1L);
    task.setTitle("Test");
    task.setStatus("In progress");
    task.setCloseDate("31/01/2000");
    task.setCreateDate("31/01/2023");
    task.setHistoric("Clean bathroom");
    
    TaskDTO taskDTO = taskMapper.toTaskDTO(task);
    assertNotEquals(taskDTOMock.getId(), taskDTO.getId());
    assertNotEquals(taskDTOMock.getName(), taskDTO.getName());
    assertNotEquals(taskDTOMock.getStage(), taskDTO.getStage());
    assertNotEquals(taskDTOMock.getEndDate(), taskDTO.getEndDate());
    assertNotEquals(taskDTOMock.getStartDate(), taskDTO.getStartDate());
    assertNotEquals(taskDTOMock.getHistoric(), taskDTO.getHistoric());
    
  }
  
  @Test
  public void testDTOtoTask () {
    
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(1L);
    taskDTO.setName("Alibio");
    taskDTO.setStartDate("01/01/2024");
    taskDTO.setEndDate("05/01/2024");
    taskDTO.setHistoric("Testing");
    taskDTO.setStage("Done");
    
    Task task = taskMapper.toTask(taskDTO);
    assertEquals(taskDTO.getId(), task.getId());
    assertEquals(taskDTO.getName(), task.getTitle());
    assertEquals(taskDTO.getStage(), task.getStatus());
    assertEquals(taskDTO.getStartDate(), task.getCreateDate());
    assertEquals(taskDTO.getEndDate(), task.getCloseDate());
    assertEquals(taskDTO.getHistoric(), task.getHistoric());
    
  }
  
  @Test
  public void testDTOtoTaskNotEquals () {
    
    Task taskMock = new Task();
    taskMock.setId(2L);
    taskMock.setTitle("Not alibio");
    taskMock.setCreateDate("05/01/2024");
    taskMock.setCloseDate("05/05/2023");
    taskMock.setStatus("In progress");
    taskMock.setHistoric("Do something");
    
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(1L);
    taskDTO.setName("Alibio");
    taskDTO.setStartDate("01/01/2024");
    taskDTO.setEndDate("05/01/2024");
    taskDTO.setHistoric("Testing");
    taskDTO.setStage("Done");
    
    Task task = taskMapper.toTask(taskDTO);
    
    assertNotEquals(taskMock.getId(), task.getId());
    assertNotEquals(taskMock.getTitle(), task.getTitle());
    assertNotEquals(taskMock.getStatus(), task.getStatus());
    assertNotEquals(taskMock.getCreateDate(), task.getCreateDate());
    assertNotEquals(taskMock.getCloseDate(), task.getCloseDate());
    assertNotEquals(taskMock.getHistoric(), task.getHistoric());
    
  }
  
  @Test
  public void testTaskListToTaskDTO () {
    
    List<Task> taskList = new ArrayList<>();
    
    Task taskMockT1 = new Task();
    taskMockT1.setId(1L);
    taskMockT1.setTitle("Test");
    taskMockT1.setStatus("In progress");
    taskMockT1.setCloseDate("31/01/2000");
    taskMockT1.setCreateDate("31/01/2023");
    taskMockT1.setHistoric("Clean bathroom");
    
    Task taskMockT2 = new Task();
    taskMockT2.setId(2L);
    taskMockT2.setTitle("Test Double");
    taskMockT2.setStatus("Done");
    taskMockT2.setCloseDate("31/05/2014");
    taskMockT2.setCreateDate("01/03/2018");
    taskMockT2.setHistoric("Clean kitchen");
    
    taskList.add(taskMockT1);
    taskList.add(taskMockT2);
    List<TaskDTO> taskDTOList = taskMapper.toTaskDTOList(taskList);
    
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(1L);
    taskDTO.setName("Alibio");
    taskDTO.setStartDate("01/01/2024");
    taskDTO.setEndDate("05/01/2024");
    taskDTO.setHistoric("Testing");
    taskDTO.setStage("Done");
    
    taskDTOList.add(taskDTO);
    
    assertNotEquals(taskDTOList.size(), taskList.size());
    
    for (int i = 0; i < taskList.size(); i++) {
      
      assertEquals(taskDTOList.get(i).getId(), taskList.get(i).getId());
      assertEquals(taskDTOList.get(i).getId(), taskList.get(i).getId());
      assertEquals(taskDTOList.get(i).getName(), taskList.get(i).getTitle());
      assertEquals(taskDTOList.get(i).getStartDate(), taskList.get(i).getCreateDate());
      assertEquals(taskDTOList.get(i).getEndDate(), taskList.get(i).getCloseDate());
      assertEquals(taskDTOList.get(i).getHistoric(), taskList.get(i).getHistoric());
      assertEquals(taskDTOList.get(i).getStage(), taskList.get(i).getStatus());
      
    }
    
  }
  
  @Test
  public void testTaskListToDtoNotEquals () {
    
    List<Task> taskList = new ArrayList<>();
    
    Task taskMockT1 = new Task();
    taskMockT1.setId(1L);
    taskMockT1.setTitle("Test");
    taskMockT1.setStatus("In progress");
    taskMockT1.setCloseDate("31/01/2000");
    taskMockT1.setCreateDate("31/01/2023");
    taskMockT1.setHistoric("Clean bathroom");
    
    Task taskMockT2 = new Task();
    taskMockT2.setId(2L);
    taskMockT2.setTitle("Test Double");
    taskMockT2.setStatus("Done");
    taskMockT2.setCloseDate("31/05/2014");
    taskMockT2.setCreateDate("01/03/2018");
    taskMockT2.setHistoric("Clean kitchen");
    
    taskList.add(taskMockT1);
    taskList.add(taskMockT2);
    
    List<TaskDTO> taskDTOList = taskMapper.toTaskDTOList(taskList);
    
    List<TaskDTO> mockedDtoList = new ArrayList<>();
    
    TaskDTO taskDtoMockedT1 = new TaskDTO();
    taskDtoMockedT1.setId(3L);
    taskDtoMockedT1.setName("The mocked Title");
    taskDtoMockedT1.setStage("Not a test");
    taskDtoMockedT1.setStartDate("31/01/2000");
    taskDtoMockedT1.setEndDate("31/01/2023");
    taskDtoMockedT1.setHistoric("The historic test");
    
    TaskDTO taskDtoMockedT2 = new TaskDTO();
    taskDtoMockedT2.setId(4L);
    taskDtoMockedT2.setName("The 2° mocked Title");
    taskDtoMockedT2.setStage("This is a test");
    taskDtoMockedT2.setStartDate("00/00/0000");
    taskDtoMockedT2.setEndDate("00/00/0000");
    taskDtoMockedT2.setHistoric("The historic test");
    
    mockedDtoList.add(taskDtoMockedT1);
    mockedDtoList.add(taskDtoMockedT2);
    
    for (int i = 0; i < taskDTOList.size(); i++) {
      
      assertNotEquals((taskDTOList.get(i).getId()), mockedDtoList.get(i).getId());
      assertNotEquals(taskDTOList.get(i).getName(), mockedDtoList.get(i).getName());
      assertNotEquals(taskDTOList.get(i).getStartDate(), mockedDtoList.get(i).getStartDate());
      assertNotEquals(taskDTOList.get(i).getEndDate(), mockedDtoList.get(i).getEndDate());
      assertNotEquals(taskDTOList.get(i).getHistoric(), mockedDtoList.get(i).getHistoric());
      assertNotEquals(taskDTOList.get(i).getStage(), mockedDtoList.get(i).getStage());
      
    }
  }
  
}
