package ra.edu.business.dao.Auth;
import ra.edu.business.model.User;

public interface authDAO {
    User login(String username, String password);
}
