package ra.edu.business.service.auth;

import ra.edu.business.dao.Auth.AuthDAOImp;
import ra.edu.business.model.User;

public class AuthServiceImp implements AuthService {
    public AuthDAOImp authDAOImp;
    public AuthServiceImp() {
        authDAOImp = new AuthDAOImp();
    }
    
    @Override
    public User login(String username, String password) {
        return authDAOImp.login(username, password);
    }
}
