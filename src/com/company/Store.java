package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Store {
    // get timestamp
    java.util.Date date = new Date();
    long time = date.getTime();
    Timestamp now = new Timestamp(time);

    // store responses
    void store(String data) {
        try {
            // database connectors/drivers
            Connection connection = DB.getConnection();
            String sqlAdd = "INSERT INTO profiles(id,data,created_at,updated_at) VALUES (?,?,?,?)";
            PreparedStatement prs = connection.prepareStatement(sqlAdd);

            //setting to database
            prs.setString(1, String.valueOf(UUID.randomUUID()));
            prs.setObject(2, data);
            prs.setTimestamp(3, now);
            prs.setTimestamp(4, now);

            prs.execute();
            prs.close();
        } catch (SQLException x) {
            new LogRequests().logRequests("Response store error " + x.getMessage());
        }
    }
}
