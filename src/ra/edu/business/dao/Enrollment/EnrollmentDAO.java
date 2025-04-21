package ra.edu.business.dao.Enrollment;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.RegisteredCurse;

import java.util.List;

public interface EnrollmentDAO extends AppDAO<Enrollment> {
    List<RegisteredCurse> listEnrollmentRegistered(int idStudent, int PageSize, int currentPage);
    int totaledEnrollment(int idStudent);
    List<RegisteredCurse> sortedEnrollment(int idStudent,String SortBy,String orderBy, int PageSize, int currentPage);
}
