package ra.edu.business.dao.Student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        return false;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    @Override
    public boolean changePassword(int id, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call changePassword(?, ?)}");
            callSt.setInt(1, id);
            callSt.setString(2, password);
            callSt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình thay đổi mật khẩu: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình thay đổi mật khẩu: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
