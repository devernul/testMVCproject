package edu.devernul.project.dao.impl;


import edu.devernul.project.dao.AbstractDaoTask;
import edu.devernul.project.model.Status;
import edu.devernul.project.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class AbstractDaoImplTask implements AbstractDaoTask {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<Task> type;

    public AbstractDaoImplTask(Class<Task> type) {
        this.type = type;
    }

    public Task getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Task t = (Task) session.load(Task.class, new Integer(id));
        return t;
    }

    public List<Task> findAll() {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Task s order by s.taskId");
        return q.list();
    }

    public List<Task> findPage(Integer start, Integer end) {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Task s order by s.status.statusId");
        List<Task> arTask =  q.list();
        if(arTask.size()<end){
            end=arTask.size();
        }
        return arTask.subList(start,end);
    }

    public Integer size() {
        Session s = sessionFactory.getCurrentSession();
        Number  n = (Number)s.createCriteria(Task.class).setProjection(Projections.rowCount()).uniqueResult();
        return  n.intValue();
    }

    public List<Task> findAllbyStatus(Status status) {
        Session s = sessionFactory.openSession();
        Query q = s.createQuery("from Task c where c.status.statusId=:id order by c.taskId");
        q.setParameter("id", status.getStatusId());
        return q.list();
    }

    public Task create(Task entity) {
        Session s = sessionFactory.getCurrentSession();
        s.save(entity);
        return entity;
    }

    public Task update(Task entity) {
        Session s = sessionFactory.getCurrentSession();
        s.update(entity);
        return entity;
    }

    public Task updateStatus(Task entity, Status status) {
        Session s = sessionFactory.getCurrentSession();
        entity.setStatus(status);
        s.update(entity);
        return entity;
    }

    public void delete(Task entity) {
        Session s = sessionFactory.getCurrentSession();
        s.delete(entity);
    }
}
