package ra.edu.business.dao.Course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;
import ra.edu.business.model.Std_status;

import java.sql.*;
import java.util.*;

public class CourseDAOImp implements CourseDAO {
    @Override
    public Course findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCourseByName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                return course;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public List<Course> listPagination(int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listCourse(?,?)}");
            callSt.setInt(1, limit);
            callSt.setInt(2, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public int totalCount() {
        Connection conn = null;
        CallableStatement callSt = null;
        int totalCourse = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalCourse()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                totalCourse = rs.getInt(1);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalCourse;
    }

    @Override
    public Course findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCourseById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                return course;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public List<Course> findByNamePagianation(String name, int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCourseByNamePagination(?,?,?)}");
            callSt.setString(1, name);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;

        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public List<Course> sortByName(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortByName(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;

        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình xắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public List<Course> sortById(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortByID(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setStatus(Std_status.valueOf(rs.getString("status")));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;

        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public int countByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        int count = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countFind(?)}");
            callSt.setString(1, name);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return count;
    }

    @Override
    public Map<Course, Integer> totalStdOfCourse(int pageSize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<Course, Integer> map = new HashMap<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalStudentOfCourse(?,?)}");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, currentPage);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                int totalStudents = rs.getInt(3);
                map.put(course, totalStudents);
            }
            return map;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return map;
    }

    @Override
    public Map<Course, Integer> top5Course() {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<Course, Integer> map = new LinkedHashMap<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call top5StdCourse()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                int totalStudents = rs.getInt(3);
                map.put(course, totalStudents);
            }
            return map;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return map;
    }

    @Override
    public Map<Course, Integer> CourseThan10Std(int pageSize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<Course, Integer> map = new HashMap<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Course10Std(?,?)}");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, currentPage);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                int totalStudents = rs.getInt(3);
                map.put(course, totalStudents);
            }
            return map;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return map;
    }

    public int countCourseThan10Std(){
        Connection conn = null;
        CallableStatement callSt = null;
        int count = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countCourse10Std()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                count = rs.getInt(1);
                return count;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return count;
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addCourse(?,?,?,?)}");
            callSt.setString(1, course.getName());
            callSt.setInt(2, course.getDuration());
            callSt.setString(3, course.getInstructor());
            callSt.setString(4, course.getStatus().name());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình thêm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thêm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateCourse(?,?,?,?)}");
            callSt.setInt(1, course.getId());
            callSt.setString(2, course.getName());
            callSt.setInt(3, course.getDuration());
            callSt.setString(4, course.getInstructor());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình cập nhật: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình cập nhật: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delCourse(?,?)}");
            callSt.setInt(1, course.getId());
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.executeUpdate();
            int return_code = callSt.getInt(2);
            if (return_code == 1) {
                return true;
            }else {
                System.out.println("\u001B[31mKhóa học đã có học viên, không thể xóa\u001B[0m");
                return false;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình xoá: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình xóa: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
