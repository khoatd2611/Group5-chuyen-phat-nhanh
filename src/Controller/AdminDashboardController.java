/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.JLabel;
import connection.MyConnection;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KhoaTran
 */
public class AdminDashboardController {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;

    ResultSet rs;

    public void fetchData(JLabel label, String typeOfData) {
        String sql = "";

        switch (typeOfData) {
            case "orders":
                sql = "SELECT COUNT(*) AS total_records FROM orders";
                break;
            case "employees":
                sql = "SELECT COUNT(*) AS total_records FROM employees";
                break;
            case "users":
                sql = "SELECT COUNT(*) AS total_records FROM user";
                break;
            case "completedOrders":
                sql = "SELECT COUNT(*) AS total_records "
                        + "FROM orders o "
                        + "JOIN delivery_history dh ON o.order_id = dh.order_id "
                        + "JOIN status s ON s.status_id = dh.status_id "
                        + "JOIN storage st ON st.storage_id = dh.storage_id "
                        + "WHERE dh.status_id = 3";
                break;
            default:
                System.out.println("Error");
        }

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("total_records");
                label.setText(Integer.toString(count));
            } else {
                System.out.println("not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        AdminDashboardController test = new AdminDashboardController();
    }
}
