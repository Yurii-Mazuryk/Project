package com.pond.project.service.dao;

import com.pond.project.service.DB.PoolConnectionBuilder;
import com.pond.project.service.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private final String SQL_INSERT_USER = "INSERT INTO " +
            "Project.users(user_login, user_password, user_name, user_phone) " +
            "VALUES (?, ?, ?, ?)";
    private final String SQL_GET_USERS = "SELECT * FROM Project.users WHERE user_id > ? LIMIT ?";
    private final String SQL_GET_SPEAKERS = "SELECT * FROM Project.users WHERE user_id > ? AND role_id = 1 LIMIT ? ";
    private final String SQL_GET_USER_BY_ID = "SELECT * FROM Project.users WHERE user_id = ?";
    private final String SQL_IS_VALID_USER_BY_LOGIN = "SELECT user_login FROM Project.users WHERE user_login = ?";
    private final String SQL_SEARCH_USER_BY_LOGIN = "SELECT * FROM Project.users WHERE user_login = ?";
    private final String SQL_GET_COUNT_OF_USERS = "SELECT COUNT(user_id) FROM Project.users";
    private final String SQL_UPDATE_ROLE_BY_LOGIN = "UPDATE Project.users SET role_id = ? WHERE user_login = ?";
    private final String SQL_UPDATE_PASSWORD_BY_ID = "UPDATE Project.users SET user_password = ? WHERE user_id = ?";
    private final String SQL_UPDATE_NAME_BY_ID = "UPDATE Project.users SET user_name = ? WHERE user_id = ?";
    private final String SQL_SPEAKERS_COUNT = "SELECT COUNT(user_name) FROM Project.users WHERE role_id = 1";

    public void insertUser(User user) {
        Connection connection = PoolConnectionBuilder.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPhoneNumber());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUserByLogin(String login) {
        User user = new User();
        Connection connection = PoolConnectionBuilder.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEARCH_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setLogin(resultSet.getString("user_login"));
            user.setName(resultSet.getString("user_name"));
            user.setId(resultSet.getInt("user_id"));
            user.setPassword(resultSet.getString("user_password"));
            user.setPhoneNumber(resultSet.getString("user_phone"));
            user.setRole(resultSet.getInt("role_id"));
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public boolean isValidLogin(String login) {
        boolean avalible = false;
        Connection connection = PoolConnectionBuilder.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_VALID_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                avalible = true;
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return avalible;
    }

    public void updateRole(String login, int role) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE_ROLE_BY_LOGIN);
            pStmt.setInt(1, role);
            pStmt.setString(2, login);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getUsers(int start, int limit) {
        List<User> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_USERS);
            pStmt.setInt(1, start);
            pStmt.setInt(2, limit);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString("user_login"));
                user.setName(resultSet.getString("user_name"));
                user.setId(resultSet.getInt("user_id"));
                user.setPassword(resultSet.getString("user_password"));
                user.setPhoneNumber(resultSet.getString("user_phone"));
                user.setRole(resultSet.getInt("role_id"));
                list.add(user);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public int getCountOfUsers() {
        int countOfUsers = 0;
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_COUNT_OF_USERS);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();
            countOfUsers = resultSet.getInt("count(user_id)");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countOfUsers;
    }

    public void changePassword(int userId, String newPassword) {
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_ID);
            pStmt.setString(1, newPassword);
            pStmt.setInt(2, userId);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = new User();
        Connection connection = PoolConnectionBuilder.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setLogin(resultSet.getString("user_login"));
            user.setName(resultSet.getString("user_name"));
            user.setId(resultSet.getInt("user_id"));
            user.setPassword(resultSet.getString("user_password"));
            user.setPhoneNumber(resultSet.getString("user_phone"));
            user.setRole(resultSet.getInt("role_id"));
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void changeUserName(int userId, String name){
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE_NAME_BY_ID);
            pStmt.setString(1, name);
            pStmt.setInt(2, userId);
            pStmt.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getSpeakers(int start, int limit) {
        List<User> list = new ArrayList<>();
        Connection connection = new PoolConnectionBuilder().getConnection();
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_GET_SPEAKERS);
            pStmt.setInt(1, start);
            pStmt.setInt(2, limit);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString("user_login"));
                user.setName(resultSet.getString("user_name"));
                user.setId(resultSet.getInt("user_id"));
                user.setPassword(resultSet.getString("user_password"));
                user.setPhoneNumber(resultSet.getString("user_phone"));
                user.setRole(resultSet.getInt("role_id"));
                list.add(user);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public int getSpeakersCount() {
        {
            int countOfSpeakers = 0;
            Connection connection = new PoolConnectionBuilder().getConnection();
            try {
                PreparedStatement pStmt = connection.prepareStatement(SQL_SPEAKERS_COUNT);
                ResultSet resultSet = pStmt.executeQuery();
                resultSet.next();
                countOfSpeakers = resultSet.getInt("count(user_name)");
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return countOfSpeakers;
        }
    }
}
