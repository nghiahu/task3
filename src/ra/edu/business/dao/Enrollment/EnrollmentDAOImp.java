package ra.edu.business.dao.Enrollment;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.RegisteredCurse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {
    @Override
    public List<Enrollment> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addEnrollment(?,?,?)}");
            callSt.setInt(1, enrollment.getStudent_id());
            callSt.setInt(2, enrollment.getCourse_id());
            callSt.setString(3,enrollment.getStatus().toString());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        return false;
    }

    @Override
    public boolean delete(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call unsubscribe(?,?,?)}");
            callSt.setInt(1, enrollment.getStudent_id());
            callSt.setInt(2, enrollment.getCourse_id());
            callSt.registerOutParameter(3, java.sql.Types.INTEGER);
            callSt.executeUpdate();
            int return_code = callSt.getInt(3);
            if (return_code == 1) {
                return true;
            }else {
                System.out.println("Khóa học đã được xác nhận không thể hủy");
                return false;
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình hủy đăng ký: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình hủy đăng ký: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<RegisteredCurse> listEnrollmentRegistered(int idStudent, int PageSize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<RegisteredCurse> registeredCurses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call registeredEnroll(?,?,?)}");
            callSt.setInt(1, idStudent);
            callSt.setInt(2, PageSize);
            callSt.setInt(3, currentPage);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            while (rs.next()) {
                RegisteredCurse registeredCurse = new RegisteredCurse();
                registeredCurse.setId(rs.getInt("id"));
                registeredCurse.setIdCourse(rs.getInt("course_id"));
                registeredCurse.setCourseName(rs.getString("name"));
                registeredCurse.setDuration(rs.getInt("duration"));
                registeredCurse.setInstructor(rs.getString("instructor"));
                registeredCurse.setRegistrationDate(rs.getDate("registered_at").toLocalDate().atStartOfDay());
                registeredCurses.add(registeredCurse);
            }
            return registeredCurses;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return registeredCurses;
    }

    @Override
    public int totaledEnrollment(int idStudent) {
        Connection conn = null;
        CallableStatement callSt = null;
        int totaledEnrollment = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalRegiseredEnll(?)}");
            callSt.setInt(1, idStudent);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                totaledEnrollment = rs.getInt(1);
                return totaledEnrollment;
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totaledEnrollment;
    }

    @Override
    public List<RegisteredCurse> sortedEnrollment(int idStudent, String SortBy, String orderBy, int PageSize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<RegisteredCurse> registeredCurses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortRegisteredEnroll(?,?,?,?,?)}");
            callSt.setInt(1, idStudent);
            callSt.setString(2, SortBy);
            callSt.setString(3, orderBy);
            callSt.setInt(4, PageSize);
            callSt.setInt(5, currentPage);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            while (rs.next()) {
                RegisteredCurse registeredCurse = new RegisteredCurse();
                registeredCurse.setId(rs.getInt("id"));
                registeredCurse.setIdCourse(rs.getInt("course_id"));
                registeredCurse.setCourseName(rs.getString("name"));
                registeredCurse.setDuration(rs.getInt("duration"));
                registeredCurse.setInstructor(rs.getString("instructor"));
                registeredCurse.setRegistrationDate(rs.getDate("registered_at").toLocalDate().atStartOfDay());
                registeredCurses.add(registeredCurse);
            }
            return registeredCurses;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return registeredCurses;
    }
}
