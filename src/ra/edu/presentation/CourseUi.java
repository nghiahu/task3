package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class CourseUi {
    private static CourseServiceImp courseServiceImp;
    private static EnrollmentServiceImp enrollmentServiceImp;
    public CourseUi() {
        courseServiceImp = new CourseServiceImp();
        enrollmentServiceImp = new EnrollmentServiceImp();
    }
    public static void main(String[] args) {
        CourseUi courseUi = new CourseUi();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("\u001B[36m┌──────────────────────────────────────────┐");
            System.out.println("│              QUẢN LÝ KHÓA HỌC            │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│1. Hiển thị danh sách khóa học            │");
            System.out.println("│2. Thêm mới khóa học                      │");
            System.out.println("│3. Chỉnh sủa thông tin khóa học           │");
            System.out.println("│4. Xóa khóa học                           │");
            System.out.println("│5. Tìm kiếm khóa học                      │");
            System.out.println("│6. Sắp xếp khóa học                       │");
            System.out.println("│7. Quay về menu chính                     │");
            System.out.println("└──────────────────────────────────────────┘\u001B[0m");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayListCoursePagination(scanner);
                    break;
                case 2:
                    addNewCourse(scanner);
                    break;
                case 3:
                    updateCourse(scanner);
                    break;
                case 4:
                    deleteCourse(scanner);
                    break;
                case 5:
                    searchCourseByName(scanner);
                    break;
                case 6:
                    SortCourse(scanner);
                    break;
                case 7:
                    Exit = true;
                    break;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!\u001B[0m");
            }
        }while(!Exit) ;
    }
    public static void displayListCoursePagination(Scanner scanner) {
        courseServiceImp.listCoursesPagination(scanner);
    }
    public static void addNewCourse(Scanner scanner) {
        Course course = new Course();
        course.inputData(scanner);
        if (courseServiceImp.save(course)) {
            System.out.println("\u001B[32mThêm khóa học thành công!\u001B[0m");
        }else {
            System.out.println("\u001B[31mThêm khóa học thất bại!\u001B[0m");
        }
    }
    public static void updateCourse(Scanner scanner) {
        int inputId = enrollmentServiceImp.choiceCourse(scanner);
        Course course = courseServiceImp.findCourseById(inputId);
        if(course == null) {
            System.out.println("\u001B[31mKhông tìm thấy khóa học\u001B[0m");
        }else {
            boolean Exit = false;
            System.out.println("Khóa học muốn cập nhật: ");
            System.out.println(course.toString());
            do {
                System.out.println("\u001B[33m┌─────────────────────────────────────────┐");
                System.out.println("│          MENU CẬP NHẬT KHÓA HỌC         │");
                System.out.println("├─────────────────────────────────────────┤");
                System.out.println("│1. Cập nhật tên khóa học                 │");
                System.out.println("│2. Cập thời lượng                        │");
                System.out.println("│3. Cập nhật giảng viên phụ trách         │");
                System.out.println("│4. Thoát                                 │");
                System.out.println("└─────────────────────────────────────────┘\u001B[0m");

                int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
                    case 1:
                        course.setName(CourseValidator.validateName(scanner, true));
                        break;
                    case 2:
                        course.setDuration(Validator.validateInt(scanner,1,1000,"Nhập vào thời lượng khóa học: ", "Thời lượng"));
                        break;
                    case 3:
                        course.setInstructor(Validator.validateString(scanner,1,100,"Giảng viên phụ trách: ", "Giảng viên"));
                        break;
                    case 4:
                        Exit = true;
                        break;
                }
            }while(!Exit);
            if (courseServiceImp.update(course)) {
                System.out.println("\u001B[32mCập nhật thành công\u001B[0m");
            }else {
                System.out.println("\u001B[31mCập nhật thất bại!\u001B[0m");
            }
        }
    }
    public static void deleteCourse(Scanner scanner) {
        int inputId= enrollmentServiceImp.choiceCourse(scanner);
        Course course = courseServiceImp.findCourseById(inputId);
        if(course == null) {
            System.out.println("\u001B[31mKhông tìm thấy khóa học muốn xóa\u001B[0m");
        }else {
            System.out.println(course.toString());
            boolean Exit = false;
            while (!Exit){
                System.out.println("Bạn có chắc chắn muốn xóa khóa học này không(y/n)");
                System.out.print("Lựa chọn: ");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if (courseServiceImp.delete(course)) {
                            System.out.println("\u001B[32mXóa thành công\u001B[0m");
                        }else {
                            System.out.println("\u001B[31mXóa thất bại\u001B[0m");
                        }
                        Exit = true;
                        break;
                    case 'n':
                        System.out.println("\u001B[32mĐã hủy xóa\u001B[0m");
                        Exit = true;
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng chọn y/n\u001B[0m");
                }
            }
        }
    }
    public static void searchCourseByName(Scanner scanner) {
        String search = Validator.validateString(scanner,0,100,"Nhập tên khóa học muốn tìm: ","Tên khóa học");
        courseServiceImp.finCourseByNamePagination(scanner,search);
        boolean Exit = false;
    }
    public static void SortCourse(Scanner scanner) {
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
                    System.out.println("\u001B[31mLựa chọn không hợp lệ, vui lòng nhập lại!\u001B[0m");
            }
        } while (!Exit);
    }
}
