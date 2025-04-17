package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class AdminUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("============= MENU ADMIN ============");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký học");
            System.out.println("4. Thống kê học viên khóa học");
            System.out.println("5. Đăng xuất");
            System.out.println("=======================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    CourseUi.main(args);
                    break;
                case 2:
                    ManagerStudentUI.main(args);
                    break;
                case 3:
                    EnrollmentUI.main(args);
                    break;
                case 4:
                    Dashboard.main(args);
                    break;
                case 5:
                    Exit = true;
                    ManiApplication.currentUser = null;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 5!");
            }
        }while (!Exit);
    }
}
