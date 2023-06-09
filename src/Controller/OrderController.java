/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import dao.OrderDao;
import dao.PackageDao;
import dao.PackageTypeDao;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KhoaTran
 */
public class OrderController {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    private PackageDao packageDao;
    private OrderDao orderDao;
    String[] northSideList = {"Lào Cai", "Yên Bái", "Lai Châu", "Điện Biên", "Sơn La", "Hòa Bình", "Hà Giang",
        "Tuyên Quang", "Phú Thọ", "Thái Nguyên", "Bắc Kạn", "Cao Bằng", "Lạng Sơn", "Bắc Giang", "Quảng Ninh",
        "Hà Nội", "Hải Phòng", "Vĩnh Phúc", "Bắc Ninh", "Hưng Yên", "Hải Dương", "Thái Bình", "Nam Định",
        "Ninh Bình", "Hà Nam"};
    String[] southSideList = {"Thành phố Hồ Chí Minh", "Đồng Nai", "Bà Rịa-Vũng Tàu", "Bình Dương", "Bình Phước",
        "Tây Ninh", "Cần Thơ", "Long An", "Tiền Giang", "Bến Tre", "Vĩnh Long", "Trà Vinh", "Đồng Tháp",
        "An Giang", "Kiên Giang", "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau"};
    String[] midSideList = {"Thanh Hóa", "Nghệ An", "Hà Tĩnh", "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế",
        "Đà Nẵng", "Quảng Nam", "Quảng Ngãi", "Bình Định", "Phú Yên", "Khánh Hòa", "Ninh Thuận",
        "Bình Thuận", "Kon Tum", "Gia Lai", "Đắk Lắk", "Đắk Nông", "Lâm Đồng"};

    ArrayList<String> northSide = new ArrayList<>(Arrays.asList(northSideList));
    ArrayList<String> midSide = new ArrayList<>(Arrays.asList(midSideList));
    ArrayList<String> southSide = new ArrayList<>(Arrays.asList(southSideList));

    public OrderController() {
        packageDao = new PackageDao();
        orderDao = new OrderDao();
    }

    public int createOrderWithPackage(int userId, String weight, String size, int typeId, String content, String deliveryType, String cost, Timestamp pickupTime, String customerName, String customerAddress, String customerPhone) {
        // Tạo gói hàng mới
//        packageDao.createPackage( weight, size, typeId, content, deliveryType, cost);

        // Kiểm tra gói hàng đã được tạo thành công chưa
        int packageIdExists = packageDao.createPackage(weight, size, typeId, content, deliveryType, cost);

        if (packageIdExists > 0) {
            // Gói hàng đã tồn tại, tiến hành tạo đơn hàng mới
            int orderID = orderDao.createOrder(userId, packageIdExists, pickupTime, customerName, customerAddress, customerPhone);
//            orderDao.
            return orderID;
        } else {
            System.out.println("Failed to create package. Cannot proceed with order creation.");
        }
        return -1;
    }

    public void findOrder(JTable table, int id) {
        try {
            String sql = "SELECT *\n"
                    + "FROM orders o\n"
                    + "JOIN delivery_history dh ON o.order_id = dh.order_id\n"
                    + "JOIN status s ON s.status_id = dh.status_id\n"
                    + "JOIN storage st ON st.storage_id = dh.storage_id\n"
                    + "WHERE dh.status_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println(ps);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[12];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getTimestamp(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getTimestamp(10);
                row[9] = rs.getString(14);
                row[10] = rs.getString(16);
                row[11] = rs.getString(17);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void findOrder(JTable table, int statusId, int userId) {
        try {
            String sql = """
                         SELECT *
                         FROM orders o
                         JOIN delivery_history dh ON o.order_id = dh.order_id
                         JOIN status s ON s.status_id = dh.status_id
                         JOIN storage st ON st.storage_id = dh.storage_id
                         WHERE dh.status_id = ? and o.user_id = ? """;
            ps = con.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            System.out.println("vao day chuawsssss");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(3);
                row[2] = rs.getTimestamp(4);
                row[3] = rs.getString(5);
                row[4] = rs.getString(6);
                row[5] = rs.getString(7);
                row[6] = rs.getTimestamp(10);
                row[7] = rs.getString(14);
                row[8] = rs.getString(16);
                row[9] = rs.getString(17);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int costOfDistance(String pickupLocation, String destination) {
        int startLocation = 0, endLocation = 0;
        if (northSide.contains(pickupLocation)) {
            startLocation = 0;
        } else if (midSide.contains(pickupLocation)) {
            startLocation = 1;
        } else if (southSide.contains(pickupLocation)) {
            startLocation = 2;
        }

        if (northSide.contains(destination)) {
            endLocation = 0;
        } else if (midSide.contains(destination)) {
            endLocation = 1;
        } else if (southSide.contains(destination)) {
            endLocation = 2;
        }

        int res = Math.abs(endLocation - startLocation);
        switch (res) {
            case 0 -> {
                return 30000;
            }
            case 1 -> {
                return 60000;
            }
            case 2 -> {
                return 90000;
            }
            default -> {
            }
        }
        return 0;
    }

    public int finalPrice(int weight, String size, String deliType, String pickupLocation, String destination) {
        int distanceCost = costOfDistance(pickupLocation, destination);
        int sizeCoefficient = 0;
        int deliTypeCoefficient = 0;
        switch (size) {
            case "Small" -> {
                sizeCoefficient = 1;
            }
            case "Medium" -> {
                sizeCoefficient = 2;
            }
            case "Big" -> {
                sizeCoefficient = 3;
            }
            default -> {
            }
        }
        switch (deliType) {
            case "Free ship 0đ" -> {
                deliTypeCoefficient = 1;
            }
            case "Basic" -> {
                deliTypeCoefficient = 2;
            }
            case "Fast Extra" -> {
                deliTypeCoefficient = 3;
            }
            default -> {
            }
        }
        int totalPrice = weight * distanceCost * sizeCoefficient * deliTypeCoefficient;
        return totalPrice;
    }

    public static void main(String[] args) {
//        OrderController orderController = new OrderController();
        // Test tạo đơn hàng mới với gói hàng
//        int orderId = 2;
//        String userId = "1";
//        String weight = "10 kg";
//        String size = "30x20x10";
//        int typeId = 11;
//        String content = "Books";
//        String deliveryType = "Express";
//        String cost = "$20";
//        Timestamp pickupTime = Timestamp.valueOf("2023-06-21 10:00:00");
//        String customerName = "John Doe";
//        String customerAddress = "123 Street";
//        String customerPhone = "123456789";
//        orderController.createOrderWithPackage(orderId, userId, weight, size, typeId, content, deliveryType, cost, pickupTime, customerName, customerAddress, customerPhone);
        // Test đọc thông tin đơn hàng
//        orderController.orderDao.readOrder(orderId);
        // Test đọc thông tin gói hàng
//        orderController.packageDao.readPackage(orderId);
    }
}
