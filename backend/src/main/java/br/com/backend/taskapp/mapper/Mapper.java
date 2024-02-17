package br.com.backend.taskapp.mapper;

import br.com.backend.taskapp.dto.TaskDTO;
import br.com.backend.taskapp.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Task.class, TaskDTO.class)
                .addMapping(Task::getId, TaskDTO::setId)
                .addMapping(Task::getTitle, TaskDTO::setName)
                .addMapping(Task::getStatus, TaskDTO::setStage)
                .addMapping(Task::getHistoric, TaskDTO::setHistoric)
                .addMapping(Task::getCreateDate, TaskDTO::setStartDate)
                .addMapping(Task::getCloseDate, TaskDTO::setEndDate);

        mapper.createTypeMap(TaskDTO.class, Task.class)
                .addMapping(TaskDTO::getId, Task::setId)
                .addMapping(TaskDTO::getName, Task::setTitle)
                .addMapping(TaskDTO::getStage, Task::setStatus)
                .addMapping(TaskDTO::getHistoric, Task::setHistoric)
                .addMapping(TaskDTO::getStartDate, Task::setCreateDate)
                .addMapping(TaskDTO::getEndDate, Task::setCloseDate);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {

        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseObjList(List<O> originList, Class<D> destination) {

        List<D> destinationObject = new ArrayList<>();
        for (O o : originList) {
            destinationObject.add(mapper.map(o, destination));
        }
        return destinationObject;
    }
}
