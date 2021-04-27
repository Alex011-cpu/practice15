package com.example.practice15.services;


import com.example.practice15.models.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private final SessionFactory sessionFactory;
    private Session session;

    public GameService(SessionFactory sessionFactory) {
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

    public void addGame(Game game) {
        var transaction = session.beginTransaction();
        session.saveOrUpdate(game);
        transaction.commit();
    }

    public Game findGame(int id ) {
        return session.createQuery("select g from game g where g.id = '" + id + "'", Game.class).getSingleResult();
    }

    public List<Game> findAllGames() {
        return session.createQuery("select g from game g",Game.class).list();
    }

    public void deleteGame(int id) {
        var transaction = session.beginTransaction();
        Game game = findGame(id);
        session.delete(game);
        transaction.commit();
    }
}
