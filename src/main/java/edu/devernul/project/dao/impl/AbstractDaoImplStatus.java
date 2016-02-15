package edu.devernul.project.dao.impl;

import edu.devernul.project.dao.AbstractDaoStatus;
import edu.devernul.project.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class AbstractDaoImplStatus implements AbstractDaoStatus {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<Status> type;

    public AbstractDaoImplStatus(Class<Status> type) {
        this.type = type;
    }

    public Status getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Status p = (Status) session.load(Status.class, new Integer(id));
        return p;
    }

    public List<Status> findAll() {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Status s order by s.statusId");
        return q.list();
    }

    public Status create(Status entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    public Status update(Status entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
        return entity;
    }

    public Status getByName(String name) {
        Session s = this.sessionFactory.getCurrentSession();
        Query q;
        q = s.createQuery("from Status s where s.name=:name");
        q.setParameter("name", name);
        return (Status)q.list().get(0);
    }

    public void delete(Status entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(entity);
    }
    public void initAction(){
        create(new Status("New"));
        create(new Status("Process"));
        create(new Status("Complete"));
    }
}
