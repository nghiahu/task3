package ra.edu.business.dao.ManagerStudent;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Std_status;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerStudentDAOImp implements ManagerStudentDAO {
    @Override
    public Student findStudentByEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findStdByEmail(?)}");
            callSt.setString(1, email);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setEmail(rs.getString("email"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setStatus(Std_status.valueOf(rs.getString("status")));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                return student;
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> findAllStudentPagination(int Pagesize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<Student>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findAllStdPagination(?,?)}");
            callSt.setInt(1, Pagesize);
            callSt.setInt(2, currentPage);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setEmail(rs.getString("email"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setStatus(Std_status.valueOf(rs.getString("status")));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt");
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return students;
    }

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
}
