package ra.edu.business.dao.Auth;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Admin;
import ra.edu.business.model.Std_status;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAOImp implements authDAO {
    @Override
    public Admin loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        Admin admin = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call loginByAdmin(?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            callSt.execute();
            if (callSt.getResultSet().next()) {
                admin = new Admin();
                admin.setId(callSt.getResultSet().getInt("id"));
                admin.setUsername(callSt.getResultSet().getString("username"));
                admin.setPassword(callSt.getResultSet().getString("password"));
                return admin;
            }else {
                System.out.println("Tài khoản hoặc mật khẩu không đúng");
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình đăng nhập: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình đăng nhập: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return admin;
    }

    @Override
    public Student loginStudent(String email, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call loginByStudent(?,?,?)}");
            callSt.setString(1, email);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, java.sql.Types.INTEGER);
            callSt.execute();
            int res_code = callSt.getInt(3);
            if (res_code == 1) {
                ResultSet resultSet = callSt.getResultSet();
                if (resultSet.next()) {
                    student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setUsername(resultSet.getString("name"));
                    student.setDob(resultSet.getDate("dob").toLocalDate());
                    student.setEmail(resultSet.getString("email"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setPassword(resultSet.getString("password"));
                    student.setStatus(Std_status.valueOf(resultSet.getString("status")));
                    student.setCreate_at(resultSet.getDate("create_at").toLocalDate());
                }
            } else if (res_code == 2) {
                System.out.println("Tài khoản đã bị xóa");
            } else if (res_code == 0) {
                System.out.println("Tài khoản hoặc mật khẩu không đúng");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đăng nhập: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đăng nhập: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }
}
