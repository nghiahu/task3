package ra.edu.business.model;

public class Admin extends User {
    public Admin() {
    }

    public Admin(int id, String email, String password, Std_status status, E_role role) {
        super(id, email, password, status, role);
    }
}
