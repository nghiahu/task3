package ra.edu.presentation;

import ra.edu.business.model.Student;
import ra.edu.business.service.ManagerStudent.ManagerStudentServiceImp;
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
            System.out.println("================== Quản lý học viên ==================");
            System.out.println("1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Tìm kiếm học viên");
            System.out.println("6. Sắp xếp học viên");
            System.out.println("7. Quay về menu chính");
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
                    System.out.println("Lựa chon không hợp lệ vui lòng chọn từ 1 - 7!");
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
            System.out.println("Đã thêm mới học viên thành công");
        }else {
            System.out.println("Thêm học viên thất bại");
        }
    }
    private static void updateStudent(Scanner scanner) {
        int id = Validator.validateInt(scanner,1,1000,"Nhập vào mã học viên: ","Mã học viên");
        Student student =managerStudentServiceImp.findStudentById(id);
        if(student == null) {
            System.out.println("Học viên không tồn tại!");
        }else {
            boolean Exit = false;
            System.out.println(student.toString());
            do {
                System.out.println("======================= Menu cập nhật =========================");
                System.out.println("1. Cập nhật email");
                System.out.println("2. Cập nhật mật khẩu");
                System.out.println("3. Cập nhật tên học viên");
                System.out.println("4. Cập nhật ngày sinh");
                System.out.println("5. Cập giới tính");
                System.out.println("6. Cập nhật số điệt thoại");
                System.out.println("7. Thoát");
                int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
                    case 1:
                        student.setEmail(StudentValidator.validateEmail(scanner));
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
                        student.setPhone(StudentValidator.validatePhone(scanner));
                        break;
                    case 7:
                        Exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn lại!");
                }
            }while (!Exit);
            if(managerStudentServiceImp.update(student)) {
                System.out.println("Cập nhật thành công!");
            }else {
                System.out.println("Cập nhật thất bại");
            }
        }
    }
    public static void searchStudent(Scanner scanner) {
        managerStudentServiceImp.findStudentPagination(scanner);
    }
    public static void deleteStudent(Scanner scanner) {
        int id = Validator.validateInt(scanner,1,1000,"Nhập vào mã học viên: ","Mã học viên");
        Student student =managerStudentServiceImp.findStudentById(id);
        if(student == null) {
            System.out.println("Học viên không tồn tại!");
        }else {
            System.out.println(student.toString());
            boolean Exit = false;
            while (!Exit){
                System.out.println("Bạn có chắc chắn muốn xóa học viên này không(y/n)");
                System.out.print("Lựa chọn: ");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if (managerStudentServiceImp.delete(student)) {
                            System.out.println("Xóa thành công");
                        }else {
                            System.out.println("Xóa thất bại");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("Đã hủy xóa");
                        Exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn y/n");
                }
            }
        }
    }
    public static void sortStudent(Scanner scanner) {
        managerStudentServiceImp.sortStudentPagination(scanner);
    }
}
