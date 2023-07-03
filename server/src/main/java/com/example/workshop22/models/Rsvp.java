package com.example.workshop22.models;

import java.sql.Date;

import lombok.Data;

@Data
public class Rsvp {
    private String name;
    private String email;
    private String phone;
    private Date date;
    private String comment;
}
