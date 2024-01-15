package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("1", "1", (byte) 1);
        System.out.println("Добавлен User1");
        userService.saveUser("2", "2", (byte) 2);
        System.out.println("Добавлен User2");
        userService.saveUser("3", "3", (byte) 3);
        System.out.println("Добавлен User3");
        userService.saveUser("4", "4", (byte) 4);
        System.out.println("Добавлен User4");
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
