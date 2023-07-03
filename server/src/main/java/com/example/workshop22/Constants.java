package com.example.workshop22;

public class Constants {
    public static final String SQL_SAVE = "INSERT INTO vip (name, email, phone, date, comment) VALUES (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATEBYNAME = "UPDATE vip SET email=?, phone=?, date=?, comment=? WHERE name=?";
    public static final String SQL_UPDATEBYEMAIL = "UPDATE vip SET name=?, phone=?, date=?, comment=? WHERE email=?";
    public static final String SQL_FINDALL = "SELECT * FROM vip";
    public static final String SQL_FINDBYNAME = "SELECT * FROM vip WHERE name=?";
    public static final String SQL_FINDBYEMAIL = "SELECT * FROM vip WHERE email=?";
    public static final String SQL_COUNT = "SELECT COUNT(*) FROM vip";
}
