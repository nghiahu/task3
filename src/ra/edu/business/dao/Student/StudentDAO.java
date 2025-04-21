package ra.edu.business.dao.Student;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Student;

public interface StudentDAO extends AppDAO<Student> {
    boolean changePassword(int id, String password);
}
