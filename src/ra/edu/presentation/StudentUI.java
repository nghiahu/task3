package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class StudentUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("================== MENU HỌC VIÊN =====================");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("========================================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
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
                    Exit = true;
                    ManiApplication.currentUser = null;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!");
            }
        }while (!Exit);
    }
}
