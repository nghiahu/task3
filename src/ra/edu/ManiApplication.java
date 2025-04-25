package ra.edu;

import ra.edu.business.model.E_role;
import ra.edu.business.model.Student;
import ra.edu.business.model.User;
import ra.edu.business.service.auth.AuthServiceImp;
import ra.edu.business.service.managerStudent.ManagerStudentServiceImp;
import ra.edu.presentation.AdminUI;
import ra.edu.presentation.StudentUI;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class ManiApplication {
    private static AuthServiceImp authServiceImp;
    private static ManagerStudentServiceImp managerStudentServiceImp;
    private ManiApplication(){
        authServiceImp = new AuthServiceImp();
        managerStudentServiceImp = new ManagerStudentServiceImp();
    }
    public static User currentUser = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManiApplication app = new ManiApplication();
        try {
            String message = "\u001B[32mChào mừng bạn đến với hệ thống quản lý đào tạo!\u001B[0m\n";
            for (int i = 0; i < message.length(); i++) {
                System.out.print(message.charAt(i));
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("\nThread bị gián đoạn!");
        }

        do {
            System.out.println("\u001B[34m┌──────────────────────────────────────────────┐");
            System.out.println("│                MENU ĐĂNG NHẬP                │");
            System.out.println("├──────────────────────────────────────────────┤");
            System.out.println("│ 1. Đăng nhập                                 │");
            System.out.println("│ 2. Thoát                                     │");
            System.out.println("└──────────────────────────────────────────────┘\u001B[0m");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ vui, vui lòng nhập tử 1 - 3!");
            }
        }while (true);
    }
    public static void login(Scanner scanner){
        String email = Validator.validateEmail(scanner);
        String pass = Validator.validateString(scanner,0,255,"Nhập vào pasword: ", "Mật khẩu");
        currentUser = authServiceImp.login(email,pass);
        if(currentUser != null){
            if (currentUser.getRole().equals(E_role.ADMIN)){
                System.out.println("\n");
                System.out.println("\u001B[32mChào mừng Admin!\u001B[0m");
                AdminUI.main(null);
            }else {
                Student currentStudent = managerStudentServiceImp.findStudentById(currentUser.getId());
                System.out.println("\n");
                System.out.println("\u001B[32m"+"Chào mừng đăng nhập học viên: " + currentStudent.getName() + "\u001B[0m");
                StudentUI.main(null);
            }
        }
    }

}
