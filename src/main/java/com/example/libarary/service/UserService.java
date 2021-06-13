package com.example.libarary.service;

import com.alibaba.fastjson.JSONObject;
import com.example.libarary.bean.User;
import com.example.libarary.connection.MyConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    public boolean add(User user) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "insert into user values(?,?,?,?)";
            runner.update(connection, sql,
                    user.getUsername(), user.getPassword(),
                    user.getPhoneNumber(), user.getRole());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String username) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "delete from user where username=?";
            runner.update(connection, sql, username);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(User user) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "update user set password=?," +
                    " phone_number=?, role=? where username=?";
            runner.update(connection, sql, user.getPassword(),
                    user.getPhoneNumber(), user.getRole(), user.getUsername());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean borrowBook(String username, String bookId) {
        Date date = new Date();
        Date dueDate = new Date(date.getTime() + 1000L * 30 * 24 * 60 * 60);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String thisTime = format.format(date);
        System.out.println(thisTime);
        String dueTime = format.format(dueDate);
        System.out.println(dueTime);
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql1 = "insert into lending_info values(?,?,?,?,?,?)";
            String sql2 = "update book set quantity=quantity-1 where isbn=?";
            runner.update(connection, sql1, null, username, bookId, thisTime, dueTime, 1);
            runner.update(connection, sql2, bookId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(int id, String bookId) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql1 = "update lending_info set state = 0 where id=?";
            String sql2 = "update book set quantity=quantity+1 where isbn=?";
            runner.update(connection, sql1, id);
            runner.update(connection, sql2, bookId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String verify(String username, String password) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select username, role from user" +
                    " where username=? and password=?";
            BeanHandler<User> handler = new BeanHandler<>(User.class);
            User user = runner.query(connection, sql, handler, username, password);
            JSONObject res = new JSONObject();
            res.put("username", user.getUsername());
            res.put("role", user.getRole());
            return res.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }

    public List<User> query() {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select username, phone_number phoneNumber," +
                    " role from user";
            BeanListHandler<User> handler = new BeanListHandler<>(User.class);
            return runner.query(connection, sql, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
