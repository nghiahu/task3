package ra.edu.presentation;

import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class EnrollmentUI {
    private static EnrollmentServiceImp enrollmentServiceImp;
    public EnrollmentUI() {
        enrollmentServiceImp = new EnrollmentServiceImp();
    }
    public static void main(String[] args) {
        EnrollmentUI enrollmentUI = new EnrollmentUI();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("\u001B[36m┌────────────────────────────────────────────────┐");
            System.out.println("│           QUẢN LÝ ĐĂNG KÝ KHÓA HỌC             │");
            System.out.println("├────────────────────────────────────────────────┤");
            System.out.println("│1. Hiển thị học viên theo từng khóa học         │");
            System.out.println("│2. Thêm học viên vào khóa học                   │");
            System.out.println("│3. Từ chối học viên khỏi khóa học               │");
            System.out.println("│4. Quay về menu chính                           │");
            System.out.println("└────────────────────────────────────────────────┘\u001B[0m");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    enrollmentServiceImp.listStudentEnroll(scanner);
                    break;
                case 2:
                    enrollmentServiceImp.approveEnrollment(scanner);
                    break;
                case 3:
                    enrollmentServiceImp.deniedEnrollment(scanner);
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
