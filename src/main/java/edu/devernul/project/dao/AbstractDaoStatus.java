package edu.devernul.project.dao;

import edu.devernul.project.model.Status;

import java.util.List;

/**
 * Created by JAVA LEARN on 13.02.2016.
 */
public interface AbstractDaoStatus {
    Status getById(Integer id);

    Status getByName(String name);

    List<Status> findAll();

    Status create(Status entity);

    Status update(Status entity);

    void delete(Status entity);
    void initAction();
}
