package ra.edu;

import ra.edu.business.model.E_role;
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
            System.out.println("1. Đăng nhập");
            System.out.println("2. Thoát");
            System.out.println("==========================================================");
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
                System.out.println("Đăng nhập thành công với vai trò Admin");
                AdminUI.main(null);
            }else {
                System.out.println("Đăng nhập thành công với vai trò Học viên");
                StudentUI.main(null);
            }
        }else {
            System.out.println("Đăng nhập thất bại");
        }
    }

}
