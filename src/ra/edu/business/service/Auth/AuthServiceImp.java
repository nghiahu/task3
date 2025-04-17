package ra.edu.business.service.Auth;

import ra.edu.business.dao.Auth.AuthDAOImp;
import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

public class AuthServiceImp implements AuthService {
    public AuthDAOImp authDAOImp;
    public AuthServiceImp() {
        authDAOImp = new AuthDAOImp();
    }

    @Override
    public Admin loginAdmin(String username, String password) {
        return authDAOImp.loginAdmin(username, password);
    }

    @Override
    public Student loginStudent(String username, String password) {
        return authDAOImp.loginStudent(username, password);
    }
}
