package ra.edu;

import ra.edu.business.model.User;
import ra.edu.business.service.Auth.AuthServiceImp;
import ra.edu.presentation.AdminUI;
import ra.edu.presentation.StudentUI;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class ManiApplication {
    private static AuthServiceImp authServiceImp;
    private ManiApplication(){
        authServiceImp = new AuthServiceImp();
    }
    public static User currentUser = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManiApplication app = new ManiApplication();
        do {
            System.out.println("===================== Hệ thống quản lý đào tạo =============");
            System.out.println("1. Đăng nhập với tư cách Quản trị viên");
            System.out.println("2. Đăng nhập với tư cách Học viên");
            System.out.println("3. Thoát");
            System.out.println("==========================================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    loginAdmin(scanner);
                    if (currentUser != null) {
                        AdminUI.main(args);
                    }
                    break;
                case 2:
                    loginStudent(scanner);
                    if (currentUser != null) {
                        StudentUI.main(args);
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ vui, vui lòng nhập tử 1 - 3!");
            }
        }while (true);
    }
    public static void loginAdmin(Scanner scanner){
        System.out.println("Đăng đăng nhập với vai trò Admin");
        String username = Validator.validateString(scanner,0,50,"Nhập vào usename: ");
        String pass = Validator.validateString(scanner,0,255,"Nhập vào pasword: ");
        currentUser = authServiceImp.loginAdmin(username,pass);
        if(currentUser != null){
            System.out.println("Đăng nhập thành công");
        }else {
            System.out.println("Đăng nhập thất bại");
        }
    }
    public static void loginStudent(Scanner scanner){
        System.out.println("Đăng đăng nhập với vai trò học sinh");
        String email = Validator.validateEmail(scanner);
        String pass = Validator.validateString(scanner,0,255,"Nhập vào pasword: ");
        currentUser = authServiceImp.loginStudent(email,pass);
        if(currentUser != null){
            System.out.println("Đăng nhập thành công");
        }else {
            System.out.println("Đăng nhập thất bại");
        }
    }
}
