import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceClass extends DBConnection {

    // 1. INSERT: Adds a new Department and Student Count
    public boolean insertDB(String deptName, int totalStudent) {
        this.getConnection();
        // Updated table to 'ds' and columns to 'dept_name', 'total_student'
        String sql = "INSERT INTO ds(dept_name, total_student) VALUES(?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptName);
            ps.setInt(2, totalStudent); // Using setInt for numbers
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // 2. VIEW: Returns a list of all Departments
    public List<String> viewDB() {
        List<String> result = new ArrayList<>();
        this.getConnection();
        String sql = "SELECT dept_name, total_student FROM ds";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add("Department: " + rs.getString("dept_name") + 
                           ", Total Students: " + rs.getInt("total_student"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

    // 3. UPDATE: Updates the student count for a specific Department
    public boolean updateDB(String deptName, int totalStudent) {
        this.getConnection();
        String sql = "UPDATE ds SET total_student = ? WHERE dept_name = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, totalStudent);
            ps.setString(2, deptName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // 4. DELETE: Removes a Department by name
    public boolean deleteDB(String deptName) {
        this.getConnection();
        String sql = "DELETE FROM ds WHERE dept_name = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
}