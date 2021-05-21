package com.pond.project.service.dao;

import com.pond.project.service.DB.PoolConnectionBuilder;
import com.pond.project.service.models.Event;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class EventDao {
    private final String SQL_CREATE_EVENT = "INSERT INTO Project.events (date, address, name) VALUES (?, ?, ?)";
    private final String SQL_UPDATE_DATETIME_BY_NAME = "UPDATE Project.events SET date = ? WHERE name = ?";
    private final String SQL_INCREMENT_PARTICIPANT_COUNT = "UPDATE Project.events SET participant_count = participant_count + 1";
    private final String SQL_GET_EVENT_BY_NAME = "SELECT * FROM Project.events WHERE name = ?";
    private final String SQL_UPDATE_ADDRESS_BY_NAME = "UPDATE Project.events SET address = ? WHERE name = ?";
    private final String SQL_GET_COUNT_OF_EVENTS = "SELECT COUNT(event_id) FROM Project.events";
    private final String SQL_GET_COUNT_OF_FUTURE_EVENTS = "SELECT COUNT(event_id) FROM Project.events WHERE date > NOW()";
    private final String SQL_GET_EVENTS = "SELECT * FROM Project.events WHERE event_id > ? LIMIT ?";
    private final String SQL_SET_SPEAKER_FOR_EVENT = "INSERT INTO Project.event_speakers (event_id, speaker_id) VALUES (?, ?)";
    private final String SQL_GET_CLOSEST_EVENT = "SELECT * FROM Project.events WHERE date > now() ORDER BY date LIMIT 1";
    private final String SQL_GET_FUTURE_EVENTS = "SELECT * FROM Project.events WHERE date > now()";
    private final String SQL_GET_MEMBER_EVENTS = "SELECT Project.events.name, Project.events.date, Project.events.address FROM Project.events " +
            "INNER JOIN Project.event_participant p WHERE p.user_id = ? AND p.event_id = Project.events.event_id";
    private final String SQL_COUNT_OF_MEMBER_EVENTS = "SELECT COUNT(user_id) FROM Project.event_participant WHERE user_id = ?";
    private final String SQL_COUNT_OF_EVENT_PARTICIPANT = "SELECT COUNT(event_id) FROM Project.event_participant WHERE event_id = ?";
    public void insertEvent(Event event) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_CREATE_EVENT);
            pStmt.setString(1, event.getDate());
            pStmt.setString(2, event.getAddress());
            pStmt.setString(3, event.getName());
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setSpeakerForEvent(int event_id, int speaker_id) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SET_SPEAKER_FOR_EVENT);
            pStmt.setInt(1, event_id);
            pStmt.setInt(2, speaker_id);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateDatetimeByName(String name,String date) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {

            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE_DATETIME_BY_NAME);
            pStmt.setString(1, date);
            pStmt.setString(2, name);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void incrementParticipantCount() {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_INCREMENT_PARTICIPANT_COUNT);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Event getEventByName(String name) {
        Event event = new Event();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_EVENT_BY_NAME);
            pStmt.setString(1, name);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            event.setId(resultSet.getInt("event_id"));
            event.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").
                    format(resultSet.getTimestamp("date").toLocalDateTime()));
            event.setName(resultSet.getString("name"));
            event.setCountOfParticipant(resultSet.getInt("participant_count"));
            event.setCountOfReports(resultSet.getInt("report_count"));
            event.setAddress(resultSet.getString("address"));
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return event;
    }

    public void updateAddressByName(String name, String address) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE_ADDRESS_BY_NAME);
            pStmt.setString(1, address);
            pStmt.setString(2, name);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int getCountOfEvents() {
        int countOfEvents = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_COUNT_OF_EVENTS);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            countOfEvents = resultSet.getInt("count(event_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countOfEvents;
    }

    public List<Event> getEvents(int start, int limit) {
        List<Event> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_EVENTS);
            pStmt.setInt(1, start);
            pStmt.setInt(2, limit);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("event_id"));
                event.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").
                        format(resultSet.getTimestamp("date").toLocalDateTime()));
                event.setName(resultSet.getString("name"));
                event.setCountOfReports(resultSet.getInt("reports_count"));
                event.setCountOfParticipant(resultSet.getInt("participant_count"));
                event.setAddress(resultSet.getString("address"));
                list.add(event);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Event closest(){
        Event event = new Event();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_CLOSEST_EVENT);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            event.setId(resultSet.getInt("event_id"));
            event.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").
                    format(resultSet.getTimestamp("date").toLocalDateTime()));
            event.setName(resultSet.getString("name"));
            event.setCountOfParticipant(resultSet.getInt("participant_count"));
            event.setCountOfReports(resultSet.getInt("reports_count"));
            event.setAddress(resultSet.getString("address"));
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return event;
    }


    public List<Event> getFutureEvents(int start, int end) {
        List<Event> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_FUTURE_EVENTS);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("event_id"));
                event.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").
                        format(resultSet.getTimestamp("date").toLocalDateTime()));
                event.setName(resultSet.getString("name"));
                event.setCountOfReports(resultSet.getInt("reports_count"));
                event.setCountOfParticipant(resultSet.getInt("participant_count"));
                event.setAddress(resultSet.getString("address"));
                list.add(event);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Event> getMemberEvents(int user_id, int start, int limit) {
        List<Event> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_MEMBER_EVENTS);
            pStmt.setInt(1, user_id);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("event_id"));
                event.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").
                        format(resultSet.getTimestamp("date").toLocalDateTime()));
                event.setName(resultSet.getString("name"));
                event.setCountOfReports(resultSet.getInt("reports_count"));
                event.setCountOfParticipant(resultSet.getInt("participant_count"));
                event.setAddress(resultSet.getString("address"));
                list.add(event);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public int getCountOfFutureEvents() {
        int countOfFutureEvents = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_COUNT_OF_FUTURE_EVENTS);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            countOfFutureEvents = resultSet.getInt("COUNT(event_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countOfFutureEvents;
    }

    public int getCountOfMemberEvents(int user_id) {
        int countOfFutureEvents = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_COUNT_OF_MEMBER_EVENTS);
            pStmt.setInt(1, user_id);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            countOfFutureEvents = resultSet.getInt("COUNT(user_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countOfFutureEvents;
    }

    public int getCountOfEventParticipant(int event_id) {
        int countOfEventParticipant = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_COUNT_OF_EVENT_PARTICIPANT);
            pStmt.setInt(1, event_id);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            countOfEventParticipant = resultSet.getInt("COUNT(event_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countOfEventParticipant;
    }
}
