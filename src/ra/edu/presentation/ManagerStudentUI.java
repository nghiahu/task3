package ra.edu.presentation;

import ra.edu.business.model.Student;
import ra.edu.business.service.managerStudent.ManagerStudentServiceImp;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class ManagerStudentUI {
    private static ManagerStudentServiceImp managerStudentServiceImp;
    public ManagerStudentUI() {
        managerStudentServiceImp = new ManagerStudentServiceImp();
    }
    public static void main(String[] args) {
        ManagerStudentUI managerStudentUI = new ManagerStudentUI();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("\u001B[36m┌────────────────────────────────────────────────────────┐");
            System.out.println("│                      QUẢN LÝ HỌC VIÊN                  │");
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.println("│1. Hiển thị danh sách học viên                          │");
            System.out.println("│2. Thêm mới học viên                                    │");
            System.out.println("│3. Chỉnh sửa thông tin học viên                         │");
            System.out.println("│4. Xóa học viên                                         │");
            System.out.println("│5. Tìm kiếm học viên                                    │");
            System.out.println("│6. Sắp xếp học viên                                     │");
            System.out.println("│7. Quay về menu chính                                   │");
            System.out.println("└────────────────────────────────────────────────────────┘\u001B[0m");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayListStdPagination(scanner);
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    searchStudent(scanner);
                    break;
                case 6:
                    sortStudent(scanner);
                    break;
                case 7:
                    Exit = true;
                    break;
                default:
                    System.out.println("\u001B[31mLựa chon không hợp lệ vui lòng chọn từ 1 - 7!\u001B[0m");
            }
        }while (!Exit);
    }
    public static void displayListStdPagination(Scanner scanner) {
        managerStudentServiceImp.findAllStudentAllPagination(scanner);
    }
    public static void addStudent(Scanner scanner) {
        Student student = new Student();
        student.inputDate(scanner);
        if(managerStudentServiceImp.save(student)) {
            System.out.println("\u001B[32mĐã thêm mới học viên thành công\u001B[0m");
        }else {
            System.out.println("\u001B[31mThêm học viên thất bại\u001B[0m");
        }
    }
    private static void updateStudent(Scanner scanner) {
        int id = managerStudentServiceImp.choiceStudentPagination(scanner);
        Student student =managerStudentServiceImp.findStudentById(id);
        if(student == null) {
            System.out.println("\u001B[31mHọc viên không tồn tại!\u001B[0m");
        }else {
            boolean Exit = false;
            System.out.println(student.toString());
            do {
                System.out.println("\u001B[33m┌─────────────────────────────────────┐");
                System.out.println("│             MENU CẬP NHẬT           │");
                System.out.println("├─────────────────────────────────────┤");
                System.out.println("│1. Cập nhật email                    │");
                System.out.println("│2. Cập nhật mật khẩu                 │");
                System.out.println("│3. Cập nhật tên học viên             │");
                System.out.println("│4. Cập nhật ngày sinh                │");
                System.out.println("│5. Cập giới tính                     │");
                System.out.println("│6. Cập nhật số điệt thoại            │");
                System.out.println("│7. Thoát                             │");
                System.out.println("└─────────────────────────────────────┘\u001B[0m");
                int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
                    case 1:
                        student.setEmail(StudentValidator.validateEmail(scanner, true));
                        break;
                    case 2:
                        student.setPassword(StudentValidator.validatePassword(scanner));
                        break;
                    case 3:
                        student.setName(Validator.validateString(scanner,1,100,"Nhập vào tên học viên: ","Tên học viên"));
                        break;
                    case 4:
                        student.setDob(StudentValidator.validateBirthday(scanner));
                        break;
                    case 5:
                        student.setSex(StudentValidator.validateGender(scanner));
                        break;
                    case 6:
                        student.setPhone(StudentValidator.validatePhone(scanner, true));
                        break;
                    case 7:
                        Exit = true;
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng chọn lại!\u001B[0m");
                }
            }while (!Exit);
            if(managerStudentServiceImp.update(student)) {
                System.out.println("\u001B[32mCập nhật thành công!\u001B[0m");
            }else {
                System.out.println("\u001B[31mCập nhật thất bại\u001B[0m");
            }
        }
    }
    public static void searchStudent(Scanner scanner) {
        managerStudentServiceImp.findStudentPagination(scanner);
    }
    public static void deleteStudent(Scanner scanner) {
        int id = managerStudentServiceImp.choiceStudentPagination(scanner);
        Student student =managerStudentServiceImp.findStudentById(id);
        if(student == null) {
            System.out.println("\u001B[31mHọc viên không tồn tại!\u001B[0m");
        }else {
            System.out.println(student.toString());
            boolean Exit = false;
            while (!Exit){
                System.out.println("Bạn có chắc chắn muốn xóa học viên này không(y/n)");
                System.out.print("\u001B[35m➤Lựa chọn(y/n): \u001B[0m");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if (managerStudentServiceImp.delete(student)) {
                            System.out.println("\u001B[32mXóa thành công\u001B[0m");
                        }else {
                            System.out.println("\u001B[31mXóa thất bại\u001B[0m");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("\u001B[32mĐã hủy xóa\u001B[0m");
                        Exit = true;
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng chọn y/n\u001B[0m");
                }
            }
        }
    }
    public static void sortStudent(Scanner scanner) {
        managerStudentServiceImp.sortStudentPagination(scanner);
    }
}
