package ra.edu.business.service.Student;

import ra.edu.business.model.Enrollment;
import ra.edu.business.service.AppService;

import java.util.Scanner;

public interface StudentService extends AppService<Enrollment> {
    void changePassword(Scanner scanner);
}
