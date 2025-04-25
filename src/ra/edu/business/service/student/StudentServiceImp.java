package ra.edu.business.service.student;

import ra.edu.ManiApplication;
import ra.edu.business.dao.Student.StudentDAOImp;
import ra.edu.business.model.Enrollment;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImp implements StudentService {
    private StudentDAOImp studentDAOImp;
    public StudentServiceImp() {
        studentDAOImp = new StudentDAOImp();
    }
    @Override
    public List<Enrollment> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Enrollment enrollment) {
        return false;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        return false;
    }

    @Override
    public boolean delete(Enrollment enrollment) {
        return false;
    }

    @Override
    public void changePassword(Scanner scanner) {
        boolean Exit = false;
        while (!Exit) {
            String email = Validator.validateEmail(scanner);
            String password = Validator.validateString(scanner,1,255,"Nhập vào mật khẩu cũ: ","Mật khẩu cũ").trim();
            if (!password.equals(ManiApplication.currentUser.getPassword())){
                System.out.println("\u001B[31mMật khẩu cũ không chính xác, vui lòng thử lại!\u001B[0m");
            } else {
                if(!email.equals(ManiApplication.currentUser.getEmail())) {
                    System.out.println("\u001B[31mEmail không chính xác vui lòng thử lại\u001B[0m");
                } else {
                    Exit = true;
                }
            }
        }
        String newPassword = StudentValidator.validatePassword(scanner);
        if(studentDAOImp.changePassword(ManiApplication.currentUser.getId(), newPassword)){
            System.out.println("Cập nhật mật khẩu mới thành công");
        }else {
            System.out.println("Cập nhật mật khẩu thất bại");
        }
    }
}
