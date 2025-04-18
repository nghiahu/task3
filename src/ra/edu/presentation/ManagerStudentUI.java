package ra.edu.presentation;

import ra.edu.business.service.ManagerStudent.ManagerStudentServiceImp;
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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
}
