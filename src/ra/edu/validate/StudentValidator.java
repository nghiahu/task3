package ra.edu.validate;

import ra.edu.business.model.Student;
import ra.edu.business.service.managerStudent.ManagerStudentServiceImp;

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
                System.out.println("\u001B[31mEmail đã tồn tại vui lòng nhập lại!\u001B[0m");
            }
        }
    }
    public static String validatePassword(Scanner scanner){
        while(true){
            System.out.print("\u001B[37mNhập mật khẩu: \u001B[0m");
            String password = scanner.nextLine();
            String regexPass = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
            if(password.isEmpty()){
                System.out.println("\u001B[31mMật khẩu không được để trống!\u001B[0m");
            } else if (!password.matches(regexPass)){
                if (password.length() < 8){
                    System.out.println("\u001B[31mMật khẩu phải phải có ít nhất 8 ký tự!\u001B[0m");
                }
                if(!password.matches(".*[0-9].*")){
                    System.out.println("\u001B[31mMật khẩu phải có ít nhất một chữ số!\u001B[0m");
                }
                if(!password.matches(".*[a-z].*")){
                    System.out.println("\u001B[31mMật khẩu phải có ít nhất một chữ viết thường!\u001B[0m");
                }
                if(!password.matches(".*[A-Z].*")){
                    System.out.println("\u001B[31mMật khẩu phải có ít nhất một chữ viết hoa!\u001B[0m");
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
                System.out.println("\u001B[31mNgày sinh không thể lớn hơn ngày hiện tại!\u001B[0m");
            }
        }
    }
    public static boolean validateGender(Scanner scanner){
        do {
            System.out.println("\u001B[33mGiới tính: \u001B[0m");
            System.out.println("\u001B[33m1. Nam\u001B[0m");
            System.out.println("\u001B[33m2. Nữ\u001B[0m");
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
                System.out.println("\u001B[31mSố tài điện thoại đã tồn tại, vui lòng thử lại!\u001B[0m");
            }
        }
    }
}
