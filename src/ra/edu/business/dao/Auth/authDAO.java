package ra.edu.business.dao.Auth;

import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

public interface authDAO {
    Admin loginAdmin(String username, String password);
    Student loginStudent(String email, String password);
}
