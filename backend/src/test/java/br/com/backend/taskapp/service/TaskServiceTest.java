package br.com.backend.taskapp.service;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.mapper.Mapper;
import br.com.backend.taskapp.model.Task;
import br.com.backend.taskapp.repository.TaskRepository;
import br.com.backend.taskapp.utils.mocks.MockTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private TaskService taskService;
    private MockTask mockObj;

    @BeforeEach
    public void setup() {

        mockObj = new MockTask();
        repository.deleteAll();

    }

    @Test
    void testFindById() {
        Task mockTask = mockObj.mockTask();
        when(repository.findById(0L)).thenReturn(Optional.of(mockTask));

        ResponseEntity<TaskDTO> result = (ResponseEntity<TaskDTO>) taskService.findById(0L);
        log.info("result: {}", result);

        assertNotNull(result.getBody());
        assertEquals(0L, result.getBody().getId());

        verify(repository, times(1)).findById(0L);
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(1l)).thenReturn(Optional.empty());

        ResponseEntity<?> result = taskService.findById(1L);
        log.info("result: {}", result);

        assertEquals(ResponseEntity.notFound().build(), result);
        verify(repository, times(1)).findById(1L);

    }

    @Test
    void testGetAllTask() {
        List<Task> taskEntities = mockObj.mockTaskList(2);
        List<TaskDTO> taskDTOs = mockObj.mockTaskDTOList(2);

        when(repository.findAll()).thenReturn(taskEntities);

        List<ResponseEntity<?>> result = taskService.getAllTask();

        assertEquals(taskDTOs.size(), result.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        Task taskEntity = mockObj.mockTask();
        TaskDTO taskDTO = mockObj.mockTaskDTO();

        when(repository.save(any(Task.class))).thenReturn(taskEntity);

        ResponseEntity<TaskDTO> responseEntity = (ResponseEntity<TaskDTO>) taskService.create(taskDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDTO.getId(), responseEntity.getBody().getId());

        assertNotNull(responseEntity.getBody());
        assertNotNull(taskDTO);

        verify(repository, times(1)).save(any(Task.class));
    }
}

