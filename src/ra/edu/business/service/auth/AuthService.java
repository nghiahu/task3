package ra.edu.business.service.auth;
import ra.edu.business.model.User;

public interface AuthService {
    User login(String username, String password);

}
