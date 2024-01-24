package br.com.backend.taskapp.service;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.exceptions.ResourceNotFoundException;
import br.com.backend.taskapp.mapper.TaskMapper;
import br.com.backend.taskapp.mapper.mocks.MockTask;
import br.com.backend.taskapp.model.Task;
import br.com.backend.taskapp.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskService taskService;
    private MockTask mockObj;

    @BeforeEach
    public void setup() {
        mockObj = new MockTask();
    }

    @Test
    void testFindById() {
        Long taskId = 0L;
        TaskDTO taskDTO = mockObj.mockTaskDTO();
        taskDTO.setId(taskId);

        when(repository.findById(taskId)).thenReturn(Optional.of(mockObj.mockTask()));
        lenient().when(taskMapper.toTaskDTO(any())).thenReturn(taskDTO);

        TaskDTO result = taskService.findById(taskId);

        assertEquals(taskId, result.getId());

        verify(repository, times(1)).findById(taskId);
    }

    @Test
    void testFindByIdNotFound() {
        Long taskId = 1L;

        when(repository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taskService.findById(taskId));

        verify(repository, times(1)).findById(taskId);
    }

    @Test
    void testGetAllTask() {
        List<Task> taskEntities = mockObj.mockTaskList(2);
        List<TaskDTO> taskDTOs = mockObj.mockTaskDTOList(2);

        when(repository.findAll()).thenReturn(taskEntities);
        lenient().when(taskMapper.toTaskDTOList(any())).thenReturn(taskDTOs);

        List<TaskDTO> result = taskService.getAllTask();

        assertEquals(taskDTOs.size(), result.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        TaskDTO taskDTO = mockObj.mockTaskDTO();
        Task taskEntity = mockObj.mockTask();

        when(taskMapper.toTaskDTO(any())).thenReturn(taskDTO);
        when(repository.save(any(Task.class))).thenReturn(taskEntity);

        TaskDTO result = taskService.create(taskDTO);

        assertEquals(taskDTO.getId(), result.getId());

        verify(taskMapper, times(1)).toTaskDTO(taskEntity);
        verify(repository, times(1)).save(any(Task.class));
    }
}

