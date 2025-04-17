package ra.edu.presentation;

import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class EnrollmentUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("================ Quản lý đăng ký khóa học ======================");
            System.out.println("1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Thêm học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 4!");
            }
        }while (!Exit);
    }
}
