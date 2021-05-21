package com.pond.project.service.dao;

import com.pond.project.service.DB.PoolConnectionBuilder;
import com.pond.project.service.models.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDao {
    private final String SQL_INSERT_USER = "INSERT INTO " +
            "Project.users(user_login, user_password, user_name, user_phone, role_id) " +
            "VALUES (?, ?, ?, ?, ?)";
    private final String SQL_GET_USERS = "SELECT * FROM Project.users WHERE user_id > ? LIMIT ?";
    private final String SQL_SEARCH_USER_BY_ID = "SELECT * FROM Project.users WHERE user_id = ?";
    private final String SQL_IS_VALID_USER_BY_LOGIN = "SELECT user_login FROM Project.users WHERE user_login = ?";
    private final String SQL_SEARCH_USER_BY_LOGIN = "SELECT * FROM Project.users WHERE user_login = ?";
    private final String SQL_GET_COUNT_OF_USERS = "SELECT COUNT(user_id) FROM Project.users";
    private final String SQL_UPDATE_ROLE_BY_LOGIN = "UPDATE Project.users SET role_id = ? WHERE user_login = ?";

    public void insertUser(User user) {
        Connection connection = PoolConnectionBuilder.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPhoneNumber());
            ps.setInt(5, user.getRole());
            ps.executeUpdate();
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
            resultSet.next();
            String isEmpty = resultSet.getString("user_login");
            if (isEmpty == null)
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
}
