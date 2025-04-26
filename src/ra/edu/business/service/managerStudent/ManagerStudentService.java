package ra.edu.business.service.managerStudent;

import ra.edu.business.model.Student;
import ra.edu.business.service.AppService;

import java.util.Scanner;

public interface ManagerStudentService extends AppService<Student> {
    Student findStudentByEmail(String email);
    void findAllStudentAllPagination(Scanner scanner);
    int countTotalStudent();
    String findPhone(String phone);
    Student findStudentById(int id);
    void findStudentPagination(Scanner scanner);
    int countTotalFind(String email, String name, String Search);
    void sortStudentPagination(Scanner scanner);
    int countTotalSort(String sortBy, String sortOrder);
    int choiceStudentPagination(Scanner scanner);
}
