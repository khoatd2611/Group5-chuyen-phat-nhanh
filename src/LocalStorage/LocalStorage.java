/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocalStorage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author KhoaTran
 */
public class LocalStorage {

    /**
     *
     * @param id
     */
    public void saveUserId(int id) {

        try (FileOutputStream fileOut = new FileOutputStream("user.bin"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject("UserId: " + id);
            System.out.println("Saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readUserId() {
        int userId = -1;
        try (FileInputStream fileIn = new FileInputStream("user.bin"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            String data = (String) objectIn.readObject();
            String[] parts = data.split(":");
            if (parts.length == 2) {
                userId = Integer.parseInt(parts[1].trim());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public void saveEmployeeId(int id) {
        try (FileOutputStream fileOut = new FileOutputStream("employee.bin"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject("EmployeeId: " + id);
            System.out.println("Employee ID saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readEmployeeId() {
        int employeeId = -1;
        try (FileInputStream fileIn = new FileInputStream("employee.bin"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            String data = (String) objectIn.readObject();
            String[] parts = data.split(":");
            if (parts.length == 2) {
                employeeId = Integer.parseInt(parts[1].trim());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employeeId;
    }

    public static void main(String[] args) {

    }
}
