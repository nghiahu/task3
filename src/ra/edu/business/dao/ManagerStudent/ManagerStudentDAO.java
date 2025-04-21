package ra.edu.business.dao.ManagerStudent;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Student;

import java.util.List;

public interface ManagerStudentDAO extends AppDAO<Student> {
    Student findStudentByEmail(String email);
    List<Student> findAllStudentPagination(int page, int size);
    int countTotalStudent();
    String findPhone(String phone);
    Student findStudentById(int id);
    List<Student> findStdByPagation(String email, String name, String Search, int currentPage, int pageSize);
    int countTotalFind(String email, String name, String Search);
    List<Student> sortStudent(String sortBy, String sortOrder, int currentPage, int pageSize);
    int countSortStudent(String sortBy, String sortOrder);
}
