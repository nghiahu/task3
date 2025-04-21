package ra.edu.validate;

import ra.edu.business.model.Student;
import ra.edu.business.service.ManagerStudent.ManagerStudentServiceImp;

import java.time.LocalDate;
import java.util.Scanner;

public class StudentValidator {
    private static ManagerStudentServiceImp managerStudentServiceImp;
    public StudentValidator(){
        managerStudentServiceImp = new ManagerStudentServiceImp();
    }
    public static String validateEmail(Scanner scanner){
        StudentValidator studentValidator = new StudentValidator();
        while(true){
            String email = Validator.validateEmail(scanner);
            Student student = managerStudentServiceImp.findStudentByEmail(email);
            if (student == null){
                return email;
            }else {
                System.out.println("Email đã tồn tại vui lòng nhập lại!");
            }
        }
    }
    public static String validatePassword(Scanner scanner){
        while(true){
            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();
            String regexPass = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
            if(password.isEmpty()){
                System.out.println("Mật khẩu không được để trống");
            } else if (!password.matches(regexPass)){
                if (password.length() < 8){
                    System.out.println("Mật khẩu phải phải có ít nhất 8 ký tự");
                }
                if(!password.matches(".*[0-9].*")){
                    System.out.println("Mật khẩu phải có ít nhất một số");
                }
                if(!password.matches(".*[a-z].*")){
                    System.out.println("Mật khẩu phải có ít nhất một chữ viết thường");
                }
                if(!password.matches(".*[A-Z].*")){
                    System.out.println("Mật khẩu phải có ít nhất một chữ viết hoa");
                }
            }else {
                return password;
            }
        }
    }
    public static LocalDate validateBirthday(Scanner scanner){
        while(true){
            LocalDate birthday = Validator.validateDate(scanner, "Nhập vào ngày sinh(dd-MM-yyyy): ", "Ngày sinh");
            LocalDate today = LocalDate.now();
            if (birthday.isBefore(today)){
                return birthday;
            }else {
                System.out.println("Ngày sinh không thể lớn hơn ngày hiện tại");
            }
        }
    }
    public static boolean validateGender(Scanner scanner){
        do {
            System.out.println("Giới tính: ");
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
            }
        }while (true);
    }
    public static String validatePhone(Scanner scanner){
        StudentValidator studentValidator = new StudentValidator();
        while(true){
            String phone = Validator.validPhoneNumberVN(scanner);
            String isphone = managerStudentServiceImp.findPhone(phone);
            if(isphone == null){
                return phone;
            }else {
                System.out.println("Số tài điện thoại đã tồn tại, vui lòng thử lại!");
            }
        }
    }
}
