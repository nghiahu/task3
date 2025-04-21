package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.business.service.Enrollment.EnrollmentServiceImp;
import ra.edu.business.service.Student.StudentServiceImp;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class StudentUI {
    private static CourseServiceImp courseServiceImp;
    private static EnrollmentServiceImp enrollmentServiceImp;
    private static StudentServiceImp studentServiceImp;
    public StudentUI() {
        courseServiceImp = new CourseServiceImp();
        enrollmentServiceImp = new EnrollmentServiceImp();
        studentServiceImp = new StudentServiceImp();
    }
    public static void main(String[] args) {
        StudentUI studentUI = new StudentUI();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("================== MENU HỌC VIÊN =====================");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Tìm kiếm khóa học theo tên");
            System.out.println("3. Đăng ký khóa học");
            System.out.println("4. Xem khóa học đã đăng ký");
            System.out.println("5. Sắp sếp khóa học");
            System.out.println("6. Hủy đăng ký");
            System.out.println("7. Đổi mật khẩu");
            System.out.println("8. Đăng xuất");
            System.out.println("========================================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourse(scanner);
                    break;
                case 2:
                    searchCourse(scanner);
                    break;
                case 3:
                    addNewEnrollment(scanner);
                    break;
                case 4:
                    lisRegisteredCourse(scanner);
                    break;
                case 5:
                    sortRegisteredCourse(scanner);
                    break;
                case 6:
                    unregisterEnrollment(scanner);
                    break;
                case 7:
                    studentServiceImp.changePassword(scanner);
                    break;
                case 8:
                    Exit = true;
                    ManiApplication.currentUser = null;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!");
            }
        }while (!Exit);
    }
    public static void displayCourse(Scanner scanner){
        courseServiceImp.listCoursesPagination(scanner);
    }
    public static void searchCourse(Scanner scanner){
        String search = Validator.validateString(scanner,1,100,"Nhập vào tên khóa học: ","Tên khóa học");
        courseServiceImp.finCourseByNamePagination(scanner,search);
    }
    public static void addNewEnrollment(Scanner scanner){
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent_id(ManiApplication.currentUser.getId());
        int idCourse = Validator.validateInt(scanner,1,1000,"Nhập vào mã khóa học bạn muốn đăng ký: ","Mã khóa học");
        Course course = courseServiceImp.findCourseById(idCourse);
        if (course != null) {
            System.out.println("Bạn có chắc muốn đăng ký khóa học: ");
            System.out.println(course.toString());
            enrollment.setCourse_id(idCourse);
            boolean Exit = false;
            while (!Exit){
                System.out.print("Lựa chọn(y/n): ");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if(enrollmentServiceImp.save(enrollment)){
                            System.out.println("Đăng ký khóa học thành công");
                        }else {
                            System.out.println("Đăng ký khóa học thất bại");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("Đã hủy đăng ký");
                        Exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn y/n");
                }
            }
            enrollment.setCourse_id(idCourse);
        }else {
            System.out.println("Khóa học không tồn tại");
        }
    }
    public static void lisRegisteredCourse(Scanner scanner){
        enrollmentServiceImp.listEnrollmentRegistered(scanner);
    }
    public static void sortRegisteredCourse(Scanner scanner){
        enrollmentServiceImp.sortRegisteredEnrollment(scanner);
    }
    public static void unregisterEnrollment(Scanner scanner){
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent_id(ManiApplication.currentUser.getId());
        int idCourse = Validator.validateInt(scanner,1,1000,"Nhập vào mã khóa học bạn muốn đăng ký: ","Mã khóa học");
        Course course = courseServiceImp.findCourseById(idCourse);
        if (course != null) {
            System.out.println("Bạn có chắc muốn hủy đăng ký khóa học: ");
            System.out.println(course.toString());
            enrollment.setCourse_id(idCourse);
            boolean Exit = false;
            while (!Exit){
                System.out.print("Lựa chọn(y/n): ");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if(enrollmentServiceImp.delete(enrollment)){
                            System.out.println("Hủy đăng ký khóa học thành công");
                        }else {
                            System.out.println("Hủy đăng ký khóa học thất bại");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("Đã hủy thao tác hủy đăng ký");
                        Exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn y/n");
                }
            }
            enrollment.setCourse_id(idCourse);
        }else {
            System.out.println("Khóa học không tồn tại");
        }
    }
}
