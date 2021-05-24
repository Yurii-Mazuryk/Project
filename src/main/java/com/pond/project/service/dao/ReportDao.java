package com.pond.project.service.dao;

import com.pond.project.service.DB.PoolConnectionBuilder;
import com.pond.project.service.models.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportDao {
    private final String SQL_INSERT_REPORT = "INSERT INTO Project.reports (title, event_name) VALUES (?, ?)";
    private final String SQL_SET_SPEAKER = "UPDATE Project.reports SET speaker_login = ?, is_report_confirmed = 1 WHERE report_id = ?";
    private final String SQL_GET_REPORT_BY_TITLE = "SELECT * FROM Project.reports WHERE title = ?";
    private final String SQL_GET_FREE_REPORTS = "SELECT * FROM Project.reports WHERE speaker_login = 0 AND report_id > ? LIMIT ?";
    private final String SQL_GET_COUNT_OF_FREE_REPORTS = "SELECT COUNT(report_id) FROM Project.reports where speaker_login = 0";
    private final String SQL_GET_REPORTS = "SELECT * FROM Project.reports WHERE report_id > ? LIMIT ?";
    private final String SQL_GET_COUNT_OF_REPORTS = "SELECT COUNT(report_id) FROM Project.reports";
    private final String SQL_SET_OFFER_FOR_REPORT = "INSERT INTO Project.speaker_reports_offers (speaker_login, report_id) " +
            "VALUES (? , ?)";
    private final String SQL_INCREMENT_OFFERS_COUNT = "UPDATE Project.reports SET offers_count = offers_count + 1 WHERE report_id = ?";
    private final String SQL_GET_SPEAKER_OFFERS = "SELECT speaker_login FROM Project.speaker_reports_offers WHERE report_id = ?";
    private final String SQL_UPDATE_TITLE = "UPDATE Project.reports SET title = ? WHERE title = ?";
    private final String SQL_REMOVE_OFFERS = "DELETE FROM Project.speaker_reports_offers WHERE report_id = ?";

    public void insertReport(Report report) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_INSERT_REPORT);
            pStmt.setString(1, report.getTitle());
            pStmt.setString(2, report.getEventName());
            pStmt.executeUpdate();
            connection.close();
            new EventDao().incrementReportsCount(report.getEventName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Report> getFreeReports(int start, int limit) {
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

    public int getCountOfAllReports() {
        int count = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_COUNT_OF_REPORTS);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            count = resultSet.getInt("COUNT(report_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
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

    public void setSpeaker(int reportId, String speakerLogin) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SET_SPEAKER);
            pStmt.setString(1, speakerLogin);
            pStmt.setInt(2, reportId);
            pStmt.executeUpdate();
            connection.close();
            removeOffers(reportId);
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
            report.setSpeakerLogin(resultSet.getString("speaker_login"));
            report.setReportConfirmed(resultSet.getBoolean("is_report_confirmed"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return report;
    }

    public List<Report> getReports(int start, int limit) {
        List<Report> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_REPORTS);
            pStmt.setInt(1, start);
            pStmt.setInt(2, limit);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                Report report = new Report();
                report.setOffersCount(resultSet.getInt("offers_count"));
                report.setTitle(resultSet.getString("title"));
                report.setId(resultSet.getInt("report_id"));
                report.setSpeakerLogin(resultSet.getString("speaker_login"));
                report.setReportConfirmed(resultSet.getBoolean("is_report_confirmed"));
                report.setEventName(resultSet.getString("event_name"));
                list.add(report);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void incrementOffersCount(int reportId) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_INCREMENT_OFFERS_COUNT);
            pStmt.setInt(1, reportId);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setOfferForReport(String speakerLogin, int reportId) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SET_OFFER_FOR_REPORT);
            pStmt.setString(1, speakerLogin);
            pStmt.setInt(2, reportId);
            pStmt.executeUpdate();
            connection.close();
            incrementOffersCount(reportId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<List<String>> getOffers(int start, int limit) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        List<List<String>> lists = new ArrayList<>();
        try {
            for (int i = 0; start + 1 <= limit; i++, start++) {
                PreparedStatement pStmt = connection.prepareStatement(SQL_GET_SPEAKER_OFFERS);
                pStmt.setInt(1, i);
                ResultSet resultSet = pStmt.executeQuery();
                lists.add(new ArrayList<>());
                while (resultSet.next()) {
                    lists.get(i).add(resultSet.getString("speaker_login"));
                }
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lists;
    }

    public void updateReportTitle(String oldTitle, String newTitle) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE_TITLE);
            pStmt.setString(1, newTitle);
            pStmt.setString(2, oldTitle);
            pStmt.executeUpdate();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void removeOffers(int reportId) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_REMOVE_OFFERS);
            pStmt.setInt(1, reportId);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public List<Report> getReportsContent(int start, int limit) {
//        Connection connection = new PoolConnectionBuilder().getConnection();
//        PreparedStatement pStmt = connection.prepareStatement(SQL_GET_CONTENT);
//    }
}
