package ra.edu.business.service.Enrollment;

import ra.edu.business.model.Enrollment;
import ra.edu.business.service.AppService;

import java.util.Scanner;

public interface EnrollmentService extends AppService<Enrollment> {
    void listEnrollmentRegistered(Scanner scanner);
    int totaledEnrollment(int idStudent);
    void sortRegisteredEnrollment(Scanner scanner);
}
