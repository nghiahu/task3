package ra.edu.business.dao.Enrollment;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.RegisteredCurse;
import ra.edu.business.model.StudentEnroll;

import java.util.List;

public interface EnrollmentDAO extends AppDAO<Enrollment> {
    List<RegisteredCurse> listEnrollmentRegistered(int idStudent, int PageSize, int currentPage);
    int totaledEnrollment(int idStudent);
    List<RegisteredCurse> sortedEnrollment(int idStudent,String SortBy,String orderBy, int PageSize, int currentPage);
    boolean isRegistered(int course_id, int student_id);
    List<StudentEnroll> listStudentEnroll(int pageSize, int currentPage);
    int totalEnrollment();
    List<StudentEnroll> listEnrollmentByCourse(int course_id, int pageSize, int currentPage);
    int countEnrollmentByCourse(int course_id);
    Enrollment finById(int id);
    boolean confirmEnrollment(int id);
    boolean cancelEnrollment(int id);
}
