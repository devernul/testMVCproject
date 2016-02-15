package edu.devernul.project.dao;

import edu.devernul.project.model.Status;
import edu.devernul.project.model.Task;

import java.util.List;

public interface AbstractDaoTask {
    Task getById(Integer id);

    List<Task> findAll();

    List<Task> findAllbyStatus(Status status);

    Task create(Task entity);

    Task update(Task entity);

    void delete(Task entity);

    public Task updateStatus(Task entity, Status status);

    public List<Task> findPage(Integer start, Integer end);

    public Integer size();
}
