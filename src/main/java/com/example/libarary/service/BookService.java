package com.example.libarary.service;

import com.example.libarary.connection.MyConnection;
import com.example.libarary.bean.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    public boolean add(Book book) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "insert into book values(?,?,?,?,?,?,?,?)";
            runner.update(connection, sql, book.getIsbn(),
                    book.getTitle(), book.getAuthor(), book.getPress(),
                    book.getType(), book.getLanguage(), book.getQuantity(), book.getIntro());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Book book) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "update book set" +
                    " title=?, author=?, press=?," +
                    " type=?, language=?," +
                    " quantity=?, intro=? where isbn=?";
            runner.update(connection, sql,
                    book.getTitle(), book.getAuthor(), book.getPress(),
                    book.getType(), book.getLanguage(),
                    book.getQuantity(), book.getIntro(), book.getIsbn());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public boolean updateImage(int id, MultipartFile file) {
//        String bookPath = ClassUtils.getDefaultClassLoader().getResource("static/book").getPath();
//        String imageName = UUID.randomUUID().toString() + ".png";
//        String imagePath = bookPath + "/" + imageName;
//        try (Connection connection = MyConnection.getConn();) {
//            QueryRunner runner = new QueryRunner();
//            File image = new File(imagePath);
//            file.transferTo(image);
//            String sql = "update book set image=? where id=?";
//            runner.update(connection, sql, imageName, id);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public boolean delete(String isbn) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "delete from book where isbn=?";
            runner.update(connection, sql, isbn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Book queryFromIsbn(String isbn) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select * from book where isbn=?";
            BeanHandler<Book> handler = new BeanHandler<>(Book.class);
            return runner.query(connection, sql, handler, isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> queryFromTitle(String title) {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select * from book where title like ?";
            BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
            String string="%"+title+"%";
            return runner.query(connection, sql, handler, string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> query() {
        try (Connection connection = MyConnection.getConn()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select * from book";
            BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
            return runner.query(connection, sql, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
