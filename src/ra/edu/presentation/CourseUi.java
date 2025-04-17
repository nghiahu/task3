package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Scanner;

public class CourseUi {
    private static CourseServiceImp courseServiceImp;
    public CourseUi() {
        courseServiceImp = new CourseServiceImp();
    }
    public static void main(String[] args) {
        CourseUi courseUi = new CourseUi();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("================= Quản lý khóa học ==============");
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sủa thông tin khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm kiếm khóa học");
            System.out.println("6. Sắp xếp khóa học");
            System.out.println("7. Quay về menu chính");
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
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!");
            }
        }while(!Exit) ;
    }
    public static void displayListCoursePagination(Scanner scanner) {
        int totalCourse = courseServiceImp.totalCourse();
        if(totalCourse == 0) {
            System.out.println("Chưa có khóa học nào!");
        }else {
            int pageSize = 5;
            int currentPage = 1;
            int totalPages = totalCourse / pageSize;
            if(totalCourse % pageSize != 0) {
                totalPages++;
            }
            boolean Exit = false;
            do {
                List<Course> listPagination = courseServiceImp.listPagination(pageSize, currentPage);
                System.out.println("Trang: " + currentPage + "/" + totalPages);
                listPagination.forEach(course -> {
                    System.out.println(course.toString());
                });
                System.out.println("1. Trang muốn xem");
                System.out.println("2. Quay lại");
                int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
                    case 1:
                        currentPage = Validator.validateInt(scanner,1,2,"Nhập trang muốn xem: ");
                        break;
                    case 2:
                        Exit = true;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lai!");
                }
            }while (!Exit);
        }
    }
    public static void addNewCourse(Scanner scanner) {
        Course course = new Course();
        course.inputData(scanner);
        if (courseServiceImp.save(course)) {
            System.out.println("Thêm khóa học thành công");
        }else {
            System.out.println("Thêm khóa học thất bại!");
        }
    }
    public static void updateCourse(Scanner scanner) {
        int inputId= Validator.validateInt(scanner,0,1000,"Nhập vào mã khóa học cần cập nhật: ");
        Course course = courseServiceImp.findCourseById(inputId);
        if(course == null) {
            System.out.println("Không tìm thấy khóa học");
        }else {
            boolean Exit = false;
            do {
                System.out.println("========================== MENU cập nhật khóa học =====================");
                System.out.println("1. Cập nhật tên khóa học");
                System.out.println("2. Cập thời lượng");
                System.out.println("3. Cập nhật giảng viên phụ trách");
                System.out.println("4. Thoát");
                int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
                    case 1:
                        course.setName(CourseValidator.validateName(scanner));
                        break;
                    case 2:
                        course.setDuration(Validator.validateInt(scanner,0,1000,"Nhập vào thời lượng khóa học: "));
                        break;
                    case 3:
                        course.setInstructor(Validator.validateString(scanner,0,100,"Giảng viên phụ trách: "));
                        break;
                    case 4:
                        Exit = true;
                        break;
                }
            }while(!Exit);
            if (courseServiceImp.update(course)) {
                System.out.println("Cập nhật thành công");
            }else {
                System.out.println("Cập nhật thất bại!");
            }
        }
    }
}
