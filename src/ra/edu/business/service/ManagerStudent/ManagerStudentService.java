package ra.edu.business.service.ManagerStudent;

import ra.edu.business.model.Student;
import ra.edu.business.service.AppService;

import java.util.Scanner;

public interface ManagerStudentService extends AppService<Student> {
    Student findStudentByEmail(String email);
    void findAllStudentAllPagination(Scanner scanner);
}
