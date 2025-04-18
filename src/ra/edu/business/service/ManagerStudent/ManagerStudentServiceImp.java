package ra.edu.business.service.ManagerStudent;

import ra.edu.business.dao.ManagerStudent.ManagerStudentDAOImp;
import ra.edu.business.model.Student;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Scanner;

public class ManagerStudentServiceImp implements ManagerStudentService {
    private ManagerStudentDAOImp managerStudentDAOImp;
    public ManagerStudentServiceImp() {
        managerStudentDAOImp = new ManagerStudentDAOImp();
    }

    @Override
    public Student findStudentByEmail(String email) {
        return managerStudentDAOImp.findStudentByEmail(email);
    }

    @Override
    public void findAllStudentAllPagination(Scanner scanner) {
        int currentPage = 1;
        int pageSize = 5;
        List<Student> studentList = managerStudentDAOImp.findAllStudentPagination(pageSize, currentPage);
        navigateToStudent(scanner, studentList);
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        return false;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    public void navigateToStudent(Scanner scanner, List<Student> students) {
        boolean Exit = false;
        do {
            if (students.isEmpty()) {
                System.out.println("Không có học sinh nào!");
                Exit = true;
            }else {
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-3s | %-20s | %-20s | %-10s | %-10s | %-22s | %-10s |\n",
                        "Mã HS", "Email","Tên học sinh", "Ngày sinh", "Giới tính", "Số điện thoại","Ngày tạo");
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                for (Student student : students) {
                    student.displayInfo();
                }
            }
            int choice = ValidatorChoice.validater(scanner);
            if (choice == 1) {
                Exit = true;
            }
        }while (!Exit);
    }
}
