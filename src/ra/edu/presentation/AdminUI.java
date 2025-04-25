package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class AdminUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("\u001B[34m┌──────────────────────────────────────────┐");
            System.out.println("│              MENU ADMIN                  │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│ 1. Quản lý khóa học                      │");
            System.out.println("│ 2. Quản lý học viên                      │");
            System.out.println("│ 3. Quản lý đăng ký học                   │");
            System.out.println("│ 4. Thống kê học viên khóa học            │");
            System.out.println("│ 5. Đăng xuất                             │");
            System.out.println("└──────────────────────────────────────────┘\u001B[0m");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    System.out.println("\n");
                    CourseUi.main(args);
                    break;
                case 2:
                    System.out.println("\n");
                    ManagerStudentUI.main(args);
                    break;
                case 3:
                    System.out.println("\n");
                    EnrollmentUI.main(args);
                    break;
                case 4:
                    System.out.println("\n");
                    Dashboard.main(args);
                    break;
                case 5:
                    Exit = true;
                    ManiApplication.currentUser = null;
                    System.out.println("\n");
                    break;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng chọn từ 1 - 5!\u001B[0m");
            }
        }while (!Exit);
    }
}
