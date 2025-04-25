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
            System.out.println("Có lỗi trong quá trình duyệt:" + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return students;
    }

    @Override
    public int countTotalStudent() {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countTotalStd()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if(rs.next()){
                total = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt: " +e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return total;
    }

    @Override
    public String findPhone(String phone) {
        Connection conn = null;
        CallableStatement callSt = null;
        String findPhone = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findPhone(?)}");
            callSt.setString(1, phone);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if(rs.next()){
                findPhone = rs.getString("phone");
                return findPhone;
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        }
        return findPhone;
    }

    @Override
    public Student findStudentById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findStdById(?)}");
            callSt.setInt(1, id);
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
    public List<Student> findStdByPagation(String email, String name, String Search, int currentPage, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call finStdPagination(?,?,?,?,?)}");
            callSt.setString(1, email);
            callSt.setString(2, name);
            callSt.setString(3, Search);
            callSt.setInt(4, pageSize);
            callSt.setInt(5, currentPage);
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
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return List.of();
    }

    @Override
    public int countTotalFind(String email, String name, String Search) {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalFind(?,?,?)}");
            callSt.setString(1, email);
            callSt.setString(2, name);
            callSt.setString(3, Search);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if(rs.next()){
                total = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return total;
    }

    @Override
    public List<Student> sortStudent(String sortBy, String sortOrder, int currentPage, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortStudent(?,?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setString(2, sortOrder);
            callSt.setInt(3, pageSize);
            callSt.setInt(4, currentPage);
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
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return students;
    }

    @Override
    public int countSortStudent(String sortBy, String sortOrder) {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countSort(?,?)}");
            callSt.setString(1, sortBy);
            callSt.setString(2, sortOrder);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if(rs.next()){
                total = rs.getInt(1);
                return total;
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return total;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addNewStudent(?,?,?,?,?,?,?,?)}");
            callSt.setString(1, student.getEmail());
            callSt.setString(2, student.getPassword());
            callSt.setString(3, student.getRole().toString());
            callSt.setString(4, student.getStatus().toString());
            callSt.setString(5, student.getName());
            callSt.setString(6, student.getDob().toString());
            callSt.setBoolean(7,student.isSex());
            callSt.setString(8, student.getPhone());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình thêm");
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình thêm: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateStudent(?,?,?,?,?,?,?)}");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getEmail());
            callSt.setString(3, student.getPassword());
            callSt.setString(4, student.getName());
            callSt.setString(5, student.getDob().toString());
            callSt.setBoolean(6, student.isSex());
            callSt.setString(7, student.getPhone());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình cập nhật");
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình cập nhật: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delStudent(?,?)}");
            callSt.setInt(1, student.getId());
            callSt.registerOutParameter(2, java.sql.Types.INTEGER);
            callSt.executeUpdate();
            int return_code = callSt.getInt(2);
            if(return_code==0){
                System.out.println("\u001B[31mHọc viên đã có khóa khóa học không thể xóa!\u001B[0m");
                return false;
            }else {
                return true;
            }
        }catch (SQLException e){
            System.out.println("Có lỗi trong quá trình xóa");
        }catch (Exception e){
            System.out.println("Có lỗi không xác định trong quá trình xóa: " + e.getMessage());
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }
}
