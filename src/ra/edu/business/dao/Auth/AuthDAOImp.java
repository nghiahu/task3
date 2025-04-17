package ra.edu.business.dao.Auth;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.E_role;
import ra.edu.business.model.User;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAOImp implements authDAO {
    @Override
    public User login(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        User user = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call loginByAccount(?,?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, java.sql.Types.INTEGER);
            callSt.execute();
            int returnCode = callSt.getInt(3);
            if (returnCode == 1) {
                ResultSet rs = callSt.getResultSet();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(E_role.valueOf(rs.getString("role")));
                    return user;
                }
            }else if (returnCode == 2) {
                System.out.println("Tài khoản đã bị xóa!");
            }else if (returnCode == 0) {
                System.out.println("Tài khoản hoặc mật khẩu không đúng");
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình đăng nhập: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình đăng nhập: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return user;
    }


}
