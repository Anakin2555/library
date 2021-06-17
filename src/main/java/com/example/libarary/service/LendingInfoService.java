package com.example.libarary.service;

import com.example.libarary.bean.LendingInfo;
import com.example.libarary.connection.MyConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LendingInfoService {
    public List<LendingInfo> queryByUsername(String username) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select lending_info.id, reader, book_id bookId, book_title bookTitle," +
                    " lend_date lendDate, due_date dueDate, state" +
                    " from lending_info join book on book.isbn=lending_info.book_id" +
                    " where reader=? order by state desc";
            BeanListHandler<LendingInfo> handler =
                    new BeanListHandler<>(LendingInfo.class);
            return runner.query(connection, sql, handler, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LendingInfo> queryAll() {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select lending_info.id, reader, book_id bookId, book_title bookTitle," +
                    " lend_date lendDate, due_date dueDate, state" +
                    " from lending_info join book on book.isbn=lending_info.book_id" +
                    " order by lendDate desc";
            BeanListHandler<LendingInfo> handler =
                    new BeanListHandler<>(LendingInfo.class);
            return runner.query(connection, sql, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LendingInfo> queryByTitle(String title) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select lending_info.id, reader, book_id bookId, book_title bookTitle," +
                    " lend_date lendDate, due_date dueDate, state" +
                    " from lending_info join book on book.isbn=lending_info.book_id" +
                    " where book_title like ? order by state desc";
            BeanListHandler<LendingInfo> handler =
                    new BeanListHandler<>(LendingInfo.class);
            String string="%"+title+"%";
            return runner.query(connection, sql, handler, string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
