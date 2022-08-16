package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("/LockTest")
public class LockTest {

    @Resource
    private DataSource dataSource;

    @GetMapping("/myTest")
    public void myTest() throws Exception{
        Connection conn = dataSource.getConnection();
        Connection conn1 = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        boolean autoCommit = conn.getAutoCommit();
        try{
            System.out.println("AutoCommit: " + autoCommit);
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement("select * from xxl_job_lock where lock_name = 'schedule_lock' for update");
            preparedStatement.execute();
            System.out.println("排他锁已打开");
            preparedStatement1 = conn1.prepareStatement("select * from xxl_job_lock where lock_name = 'schedule_lock'");
            preparedStatement1.execute();
            System.out.println(preparedStatement1.getMetaData());
            conn.setAutoCommit(autoCommit);
            conn.commit();
            conn.close();
            System.out.println("排他锁已关闭");
            preparedStatement1.execute();
            System.out.println(preparedStatement1.getMetaData());
        }finally {
            if (conn1 != null) {
                try {
                    conn1.commit();
                } catch (SQLException e) {
                }
                }
                try {
                    conn1.close();
                } catch (SQLException e) {
                }
            }
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
        }
}
