package com.pond.project.service.dao;

import com.pond.project.service.DB.PoolConnectionBuilder;
import com.pond.project.service.models.Report;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReportDao {
    private final String SQL_INSERT_REPORT = "INSERT INTO Project.reports (title, text, event_name) VALUES (?, ?, ?)";
    private final String SQL_SET_SPEAKER = "UPDATE Project.reports SET speaker_id = ? WHERE title = ?";
    private final String SQL_GET_REPORT_BY_TITLE = "SELECT * FROM Project.reports WHERE title = ?";
    private final String SQL_GET_FREE_REPORTS = "SELECT * FROM Project.reports WHERE speaker_id = 0 AND report_id > ? LIMIT ?";
    private final String SQL_GET_COUNT_OF_FREE_REPORTS = "SELECT COUNT(report_id) FROM Project.reports where speaker_id = 0";

    public void insertReport(Report report) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_INSERT_REPORT);
            pStmt.setString(1, report.getTitle());
            pStmt.setString(2, report.getText());
            pStmt.setString(3, report.getEventName());
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Report> getEvents(int start, int limit) {
        List<Report> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_FREE_REPORTS);
            pStmt.setInt(1, start);
            pStmt.setInt(2, limit);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                Report report = new Report();
                report.setTitle(resultSet.getString("title"));
                report.setId(resultSet.getInt("report_id"));
//                report.setSpeakerId(resultSet.getInt("speaker_id"));
//                report.setReportConfirmed(resultSet.getBoolean("is_report_confirmed"));
                report.setEventName(resultSet.getString("event_name"));
                list.add(report);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public int getCountOfFreeReports() {
        int count = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_COUNT_OF_FREE_REPORTS);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            count = resultSet.getInt("COUNT(report_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public void setSpeakerIdByTitle(String title,int speakerId) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SET_SPEAKER);
            pStmt.setInt(1, speakerId);
            pStmt.setString(2, title);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Report getReportByTitle(String title) {
        Report report = new Report();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_REPORT_BY_TITLE);
            pStmt.setString(1, title);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            report.setId(resultSet.getInt("report_id"));
            report.setTitle(resultSet.getString("title"));
            report.setText(resultSet.getString("text"));
            report.setSpeakerId(resultSet.getInt("speaker_id"));
            report.setReportConfirmed(resultSet.getBoolean("is_report_confirmed"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return report;
    }


}
