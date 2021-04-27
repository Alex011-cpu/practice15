package com.example.practice15.services;

import com.example.practice15.models.Game;
import com.example.practice15.models.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class LevelService {
    @Autowired
    private final SessionFactory sessionFactory;
    private Session session;

    public LevelService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addLevel(Level level) {
        var transaction = session.beginTransaction();
        session.saveOrUpdate(level);
        transaction.commit();
    }

    public Level findLevel(int id ) {
        return session.createQuery("select l from level l where l.id = '" + id + "'", Level.class).getSingleResult();
    }

    public List<Level> findAllLevels() {
        return session.createQuery("select l from level l",Level.class).list();
    }

    public void deleteLevel(int id) {
        var transaction = session.beginTransaction();
        Level level = findLevel(id);
        session.delete(level);
        transaction.commit();
    }
}
