package com.example.workshop22.repositories;

import java.util.List;

import com.example.workshop22.models.Rsvp;

public interface JDBCRepository {

    int save(Rsvp rsvp);

    int updateByName(Rsvp rsvp);

    int updateByEmail(Rsvp rsvp);

    Rsvp findByName(String name);

    Rsvp findByEmail(String email);

    List<Rsvp> findAll();

    List<Rsvp> findByNameContaining(String name);

    Integer count();
    
}
