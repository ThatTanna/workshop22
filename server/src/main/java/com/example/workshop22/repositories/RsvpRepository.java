package com.example.workshop22.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.workshop22.models.Rsvp;
import static com.example.workshop22.Constants.*;

@Repository
public class RsvpRepository implements JDBCRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int save(Rsvp rsvp) {
        return jdbcTemplate.update(SQL_SAVE,
                rsvp.getName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getComment());
    }

    public int updateByName(Rsvp rsvp) {
        return jdbcTemplate.update(SQL_UPDATEBYNAME,
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getComment(),
                rsvp.getName());
    }

    public int updateByEmail(Rsvp rsvp) {
        return jdbcTemplate.update(SQL_UPDATEBYEMAIL,
                rsvp.getName(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getComment(),
                rsvp.getEmail());

    }

    public List<Rsvp> findAll() {
        return jdbcTemplate.query(SQL_FINDALL,
                BeanPropertyRowMapper.newInstance(Rsvp.class));
    }

    public List<Rsvp> findByNameContaining(String name) {
        String sql = "SELECT * FROM vip WHERE name LIKE '%" + name + "%'";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Rsvp.class));
    }

    public Rsvp findByName(String name) {
        return findBy(SQL_FINDBYNAME, name);
    }

    public Rsvp findByEmail(String email) {
        return findBy(SQL_FINDBYEMAIL, email);
    }

    private Rsvp findBy(String sql, String identifier) {
        try {
            Rsvp rsvp = jdbcTemplate.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Rsvp.class), identifier);

            return rsvp;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public Integer count() {
        return jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
    }

    // public String register(@RequestBody Rsvp rsvp) {
    // String sql = "INSERT INTO vip (`name`, `email`, `phone`, `date`, `comment`)
    // VALUES (?, ?, ?, ?, ?)";
    // jdbcTemplate.update(sql, rsvp.getName(),
    // rsvp.getEmail(),
    // rsvp.getPhone(),
    // rsvp.getDate(),
    // rsvp.getComment());
    // System.out.println(rsvp);
    // return "REGISTERED";
    // }

}
