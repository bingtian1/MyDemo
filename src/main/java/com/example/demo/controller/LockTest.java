package com.example.demo.controller;

import com.example.demo.utils.JacksonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/LockTest")
public class LockTest {

    @Resource
    private DataSource dataSource;

    @GetMapping("/myTest")
    public void myTest() throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        boolean autoCommit = conn.getAutoCommit();
        try {
            System.out.println("AutoCommit: " + autoCommit);
            conn.setAutoCommit(false);
//            preparedStatement = conn.prepareStatement("select * from xxl_job_lock where lock_name = 'schedule_lock' for update");
            preparedStatement = conn.prepareStatement("select * from xxl_job_lock where lock_name = 'schedule_lock' ");
            preparedStatement.execute();
            System.out.println("排他锁已打开");
        } finally {
            if (conn != null) {
                try {
                    conn.commit();
                } catch (SQLException e) {
                }
                try {
                    conn.setAutoCommit(autoCommit);
                } catch (SQLException e) {
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
            System.out.println("排他锁已关闭");
        }
    }

    @GetMapping("/myTest2")
    public void myTest2() throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        boolean autoCommit = conn.getAutoCommit();
        try {
            System.out.println("线程2-AutoCommit: " + autoCommit);
            preparedStatement = conn.prepareStatement("select * from xxl_job_lock where lock_name = 'schedule_lock' for update");
            preparedStatement.execute();
            System.out.println("线程2-排他锁已打开");
        } finally {
            if (conn != null) {
                try {
                    conn.commit();
                } catch (SQLException e) {
                }
                try {
                    conn.setAutoCommit(autoCommit);
                } catch (SQLException e) {
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
            System.out.println("线程2-排他锁已关闭");
        }

    }
}