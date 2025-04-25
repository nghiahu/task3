package ra.edu.business.service.enrollment;

import ra.edu.business.model.Enrollment;
import ra.edu.business.service.AppService;

import java.util.Scanner;

public interface EnrollmentService extends AppService<Enrollment> {
    void listEnrollmentRegistered(Scanner scanner);
    int totaledEnrollment(int idStudent);
    void sortRegisteredEnrollment(Scanner scanner);
    boolean isRegistered(int idCourse, int idStudent);
    void listStudentEnroll(Scanner scanner);
    int totalEnrollment();
    void approveEnrollment(Scanner scanner);
    void deniedEnrollment(Scanner scanner);
}
