package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.business.service.student.StudentServiceImp;
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
            System.out.println("\u001B[34m┌───────────────────────────────────────────────────────────────┐");
            System.out.println("│                         MENU HỌC VIÊN                         │");
            System.out.println("├───────────────────────────────────────────────────────────────┤");
            System.out.println("│ 1. Xem danh sách khóa học                                     │");
            System.out.println("│ 2. Tìm kiếm khóa học theo tên                                 │");
            System.out.println("│ 3. Đăng ký khóa học                                           │");
            System.out.println("│ 4. Xem khóa học đã đăng ký                                    │");
            System.out.println("│ 5. Sắp xếp khóa học                                           │");
            System.out.println("│ 6. Hủy đăng ký                                                │");
            System.out.println("│ 7. Đổi mật khẩu                                               │");
            System.out.println("│ 8. Đăng xuất                                                  │");
            System.out.println("└───────────────────────────────────────────────────────────────┘\u001B[0m");
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
                    System.out.println("\u001B[31mLựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!\u001B[0m");
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
        if(course == null){
            System.out.println("\u001B[31mKhông tìm thấy khóa học!\u001B[0m");
        }
        boolean isRegistered = enrollmentServiceImp.isRegistered(idCourse, enrollment.getStudent_id());
        if (course != null && isRegistered) {
            System.out.println("Bạn có chắc muốn đăng ký khóa học: ");
            System.out.println(course.toString());
            enrollment.setCourse_id(idCourse);
            boolean Exit = false;
            while (!Exit){
                System.out.print("\u001B[35m➤ Lựa chọn(y/n): \u001B[0m");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if(enrollmentServiceImp.save(enrollment)){
                            System.out.println("\u001B[32mĐăng ký khóa học thành công\u001B[0m");
                        }else {
                            System.out.println("\u001B[31mĐăng ký khóa học thất bại\u001B[0m");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("\u001B[32mĐã hủy đăng ký\u001B[0m");
                        Exit = true;
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng chọn y/n\u001B[0m");
                }
            }
            enrollment.setCourse_id(idCourse);
        }
    }
    public static void lisRegisteredCourse(Scanner scanner){
        enrollmentServiceImp.listEnrollmentRegistered(scanner);
    }
    public static void sortRegisteredCourse(Scanner scanner){
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp khóa học");
            System.out.println("1. Theo tên");
            System.out.println("2. Theo mã khóa học");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    courseServiceImp.sortByName(scanner);
                    Exit = true;
                    break;
                case 2:
                    courseServiceImp.sortById(scanner);
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!Exit);
    }
    public static void unregisterEnrollment(Scanner scanner){
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent_id(ManiApplication.currentUser.getId());
        int idCourse = Validator.validateInt(scanner,1,1000,"Nhập vào mã khóa học bạn muốn hủy đăng ký: ","Mã khóa học");
        Course course = courseServiceImp.findCourseById(idCourse);
        if (course != null) {
            System.out.println("Bạn có chắc muốn hủy đăng ký khóa học: ");
            System.out.println(course.toString());
            enrollment.setCourse_id(idCourse);
            boolean Exit = false;
            while (!Exit){
                System.out.print("\u001B[35m➤ Lựa chọn: \u001B[0m");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if(enrollmentServiceImp.delete(enrollment)){
                            System.out.println("\u001B[32mHủy đăng ký khóa học thành công\u001B[0m");
                        }else {
                            System.out.println("\u001B[31mHủy đăng ký khóa học thất bại\u001B[0m");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("\u001B[32mĐã hủy thao tác hủy đăng ký\u001B[0m");
                        Exit = true;
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng chọn y/n\u001B[0m");
                }
            }
            enrollment.setCourse_id(idCourse);
        }else {
            System.out.println("\u001B[31mKhóa học không tồn tại!\u001B[0m");
        }
    }
}
