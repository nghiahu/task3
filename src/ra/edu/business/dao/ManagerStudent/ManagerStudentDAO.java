package ra.edu.business.dao.ManagerStudent;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Student;

import java.util.List;

public interface ManagerStudentDAO extends AppDAO<Student> {
    Student findStudentByEmail(String email);
    List<Student> findAllStudentPagination(int page, int size);
}
